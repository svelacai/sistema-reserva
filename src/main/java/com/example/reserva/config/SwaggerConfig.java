package com.example.reserva.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de Swagger/OpenAPI para la documentación de la API.
 * 
 * @author Sistema Reserva
 * @version 1.0
 */
@Configuration
public class SwaggerConfig {

    /**
     * Configura la información básica de la API para Swagger.
     * 
     * @return configuración de OpenAPI
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema de Reservas API")
                        .version("1.0")
                        .description("API para la gestión de turnos y reservas"));
    }
}