package com.prueba.tecnica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TecnicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TecnicaApplication.class, args);
    }

}
