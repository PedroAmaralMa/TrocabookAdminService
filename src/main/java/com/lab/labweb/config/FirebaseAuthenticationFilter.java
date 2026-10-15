package com.lab.labweb.config;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class FirebaseAuthenticationFilter
        extends OncePerRequestFilter {

    private final FirebaseAuth firebaseAuth;
    private final Firestore firestore;

    public FirebaseAuthenticationFilter(
            FirebaseAuth firebaseAuth,
            Firestore firestore
    ) {
        this.firebaseAuth = firebaseAuth;
        this.firestore = firestore;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authorization =
                request.getHeader("Authorization");

        if (authorization == null ||
                !authorization.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }

        String token =
                authorization.substring(7);

        try {
            FirebaseToken firebaseToken =
                    firebaseAuth.verifyIdToken(token);

            String uid = firebaseToken.getUid();

            var documento = firestore
                    .collection("usuarios")
                    .document(uid)
                    .get()
                    .get();

            if (documento.exists()) {

                String role =
                        documento.getString("role");

                if ("ADMIN".equals(role)) {

                    var authority =
                            new SimpleGrantedAuthority(
                                    "ROLE_ADMIN"
                            );

                    var authentication =
                            new UsernamePasswordAuthenticationToken(
                                    uid,
                                    null,
                                    List.of(authority)
                            );

                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(authentication);
                }
            }

        } catch (Exception e) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}