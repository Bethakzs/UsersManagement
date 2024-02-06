package com.example.demo.config;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

//http://localhost:8080/swagger-ui/index.html#/ link to swagger documatation
// Swagger
// front in docker
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .servers(
                        List.of(
                                new Server().url("http://localhost:8080")
                        )
                )
                .info(
                        new Info().title("Our users API")
                );
    }
}
