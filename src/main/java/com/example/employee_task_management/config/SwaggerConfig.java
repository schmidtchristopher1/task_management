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
                        .addSchemas("Employee", new io.swagger.v3.oas.models.media.Schema()
                                .description("Employee model")
                                .addProperties("id", new io.swagger.v3.oas.models.media.IntegerSchema())
                                .addProperties("name", new io.swagger.v3.oas.models.media.StringSchema())
                                .addProperties("email", new io.swagger.v3.oas.models.media.StringSchema())
                                .addProperties("tasks", new io.swagger.v3.oas.models.media.ArraySchema()
                                        .items(new io.swagger.v3.oas.models.media.Schema()
                                                .description("Task model")
                                                .addProperties("id", new io.swagger.v3.oas.models.media.IntegerSchema())
                                                .addProperties("title", new io.swagger.v3.oas.models.media.StringSchema())
                                                .addProperties("description", new io.swagger.v3.oas.models.media.StringSchema())))));
    }
}