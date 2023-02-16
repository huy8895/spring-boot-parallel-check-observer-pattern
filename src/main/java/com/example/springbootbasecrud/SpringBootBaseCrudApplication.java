package com.example.springbootbasecrud;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "demo swagger base crud"))
public class SpringBootBaseCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBaseCrudApplication.class, args);
    }

}
