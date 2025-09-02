package com.lab.labweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "config")
public class LaboratorioDevWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaboratorioDevWebApplication.class, args);
    }

}
