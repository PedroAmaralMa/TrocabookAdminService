package com.lab.labweb.service;

import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class JwtService {

    private final JwtEncoder jwtEncoder;

    public JwtService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String gerarTokenInterno() {

        Instant agora = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject("admin-service")
                .issuer("trocabook-admin-service")
                .audience(List.of("trocabook-api"))
                .issuedAt(agora)
                .expiresAt(
                        agora.plus(5, ChronoUnit.MINUTES)
                )
                .claim("scope", "admin:usuarios")
                .build();

        JwsHeader header = JwsHeader
                .with(SignatureAlgorithm.RS256)
                .build();

        return jwtEncoder
                .encode(
                        JwtEncoderParameters.from(
                                header,
                                claims
                        )
                )
                .getTokenValue();
    }
}