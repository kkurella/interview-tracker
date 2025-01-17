package com.iview.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean//TODO
    public OpenAPI openAPIConfig(){
        return new OpenAPI().info(new Info().title("User Management Service API")
                        .description("API for the user registration endpoints.")
                        .version("1.0"));
    }
}
