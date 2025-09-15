package com.lab.labweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LaboratorioDevWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaboratorioDevWebApplication.class, args);
    }
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
