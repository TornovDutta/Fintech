package com.example.fintech.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI fintechOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Fintech API")
                .description("Spring Boot Fintech Application API Documentation")
                .version("v0.0.1"));
    }
}
