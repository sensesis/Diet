package com.apple.shop.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addResponses("BadRequest", new ApiResponse().description("잘못된 요청입니다."))
                        .addResponses("Unauthorized", new ApiResponse().description("인증 실패입니다."))
                        .addResponses("InternalServerError", new ApiResponse().description("서버 내부 오류입니다."))
                        .addResponses("Success", new ApiResponse().description("성공적으로 요청이 처리되었습니다."))
                );
    }
}