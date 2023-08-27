package com.apiHateoas.Swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {


    public OpenAPI api(){
        return new OpenAPI().info(new Info().title("API-Cuentas").version("1.0 SNAPSHOT")
                .contact(new Contact()));
    }
}
