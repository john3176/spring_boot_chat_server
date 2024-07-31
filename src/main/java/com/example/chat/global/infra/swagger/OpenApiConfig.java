package com.example.chat.global.infra.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI(){
        Info info = new Info()
                .title("Chat Program Document")
                .version("1.0")
                .description("Chat Program");

        return new OpenAPI()
                .info(info);
    }
}
