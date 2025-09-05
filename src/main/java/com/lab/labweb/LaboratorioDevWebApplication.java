package com.lab.labweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.lab.labweb.config")
public class LaboratorioDevWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaboratorioDevWebApplication.class, args);
    }

}
