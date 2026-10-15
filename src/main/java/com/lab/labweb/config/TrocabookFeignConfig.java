package com.lab.labweb.config;

import com.lab.labweb.service.JwtService;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class TrocabookFeignConfig {

    private final JwtService jwtService;

    public TrocabookFeignConfig(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Bean
    public RequestInterceptor trocabookRequestInterceptor() {
        return requestTemplate -> {

            String token =
                    jwtService.gerarTokenInterno();

            requestTemplate.header(
                    "Authorization",
                    "Bearer " + token
            );
        };
    }
}