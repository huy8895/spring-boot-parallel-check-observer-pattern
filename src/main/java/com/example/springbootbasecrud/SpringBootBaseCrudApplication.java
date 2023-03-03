package com.example.springbootbasecrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringBootBaseCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBaseCrudApplication.class, args);
    }

}
