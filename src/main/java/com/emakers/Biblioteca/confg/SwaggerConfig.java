package com.emakers.Biblioteca.confg;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration

public class SwaggerConfig {

    @Bean
    public OpenAPI springOpenAi(){
        return new OpenAPI()
                .info( new Info().title("Biblioteca API")
                .summary("API")
                .description("Utilizando swagger na api")
                .version("1.0.0"));

    }


}
