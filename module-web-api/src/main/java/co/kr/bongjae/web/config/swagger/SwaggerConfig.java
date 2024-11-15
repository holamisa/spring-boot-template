package co.kr.bongjae.web.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(title = "Spring Boot API 명세서",
                description = "기본 API 명세서",
                version = "v1"))
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        SecurityScheme bearerScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer").bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");

        SecurityScheme basicAuthScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("basic")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");

        SecurityRequirement bearerRequirement = new SecurityRequirement().addList("bearer-token");
        SecurityRequirement basicAuthRequirement = new SecurityRequirement().addList("basic-auth");

        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-token", bearerScheme)
                        .addSecuritySchemes("basic-auth", basicAuthScheme))
                .security(Arrays.asList(bearerRequirement, basicAuthRequirement));

    }
}