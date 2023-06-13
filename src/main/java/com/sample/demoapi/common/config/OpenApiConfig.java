package com.sample.demoapi.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    @Bean
    OpenAPI openAPI() {
        Info info = new Info().title("[ Demo Application ] RESTful API Document").version("v1.0.0")
                .description("Board Application API Document");

        return new OpenAPI().components(new Components()).info(info);
    }
}


