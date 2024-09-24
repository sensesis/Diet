package com.apple.shop.swagger;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi userGroupedOpenApi() {
        List<Tag> tags = List.of(new Tag().name("UserController").description("유저 API"));

        return GroupedOpenApi
                .builder()
                .group("user 2.0")
                .pathsToMatch("/api/v2/**")
                .addOpenApiCustomizer(
                        openApi -> {
                            openApi.setTags(tags);
                            openApi.setInfo(
                                    new Info()
                                            .title("user api")
                                            .description("유저 업무 처리를 위한 API")
                                            .version("2.0.0")
                            );
                        }
                )
                .build();
    }

    @Bean
    public GroupedOpenApi groupGroupedOpenApi() {
        List<Tag> tags = List.of(new Tag().name("GroupController").description("그룹 API"));

        return GroupedOpenApi
                .builder()
                .group("group 1.0")
                .pathsToMatch("/api/v1/**")
                .addOpenApiCustomizer(
                        openApi -> {
                            openApi.setTags(tags);
                            openApi.setInfo(
                                    new Info()
                                            .title("group api")
                                            .description("그룹 업무 처리를 위한 API")
                                            .version("1.0.0")
                            );
                        }
                )
                .build();
    }
}