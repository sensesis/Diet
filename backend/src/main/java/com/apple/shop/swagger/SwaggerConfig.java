package com.apple.shop.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.*;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "00 프로젝트 API 명세서",
                description = "00 프로젝트에 사용되는 API 명세서",
                version = "v1"
        )
)
public class SwaggerConfig {

    private static final String BEARER_TOKEN_PREFIX = "Bearer";

    @Bean
    @Profile("!Prod") // 운영 환경에서는 Swagger 비활성화
    public OpenAPI openAPI() {
        String jwtSchemeName = "Authorization"; // JwtTokenProvider.AUTHORIZATION_HEADER 대체
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);
        Components components = new Components()
                .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                        .name(jwtSchemeName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme(BEARER_TOKEN_PREFIX)
                        .bearerFormat("JWT")); // JwtTokenProvider.TYPE 대체

        return new OpenAPI()
                .addSecurityItem(securityRequirement)
                .components(components);
    }

    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("User API")
                .pathsToMatch("/api/users/**")
                .build();
    }

    @Bean
    public GroupedOpenApi foodApi() {
        return GroupedOpenApi.builder()
                .group("Food API")
                .pathsToMatch("/api/foods/**")
                .build();
    }
}