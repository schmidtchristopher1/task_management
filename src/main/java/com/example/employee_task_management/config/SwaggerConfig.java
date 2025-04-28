package com.example.employee_task_management.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Employee Task Management API")
                        .description("API documentation for managing employees and tasks")
                        .version("1.0"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSchemas("Employee", new io.swagger.v3.oas.models.media.Schema<Object>()
                                .description("Employee model")
                                .addProperty("id", new io.swagger.v3.oas.models.media.IntegerSchema())
                                .addProperty("name", new io.swagger.v3.oas.models.media.StringSchema())
                                .addProperty("email", new io.swagger.v3.oas.models.media.StringSchema())
                                .addProperty("tasks", new io.swagger.v3.oas.models.media.ArraySchema()
                                        .items(new io.swagger.v3.oas.models.media.Schema<Object>()
                                                .description("Task model")
                                                .addProperty("id", new io.swagger.v3.oas.models.media.IntegerSchema())
                                                .addProperty("title", new io.swagger.v3.oas.models.media.StringSchema())
                                                .addProperty("description", new io.swagger.v3.oas.models.media.StringSchema())))));
    }
}