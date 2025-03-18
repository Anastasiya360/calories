package org.example.calories.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Сalorie calculator System",
                description = "Сalorie calculator System", version = "2.6.0",
                contact = @Contact(
                        name = "Anastasia",
                        email = "n.bogocharova@gmail.com"
                )
        )
)
public class OpenApiConfig {

}
