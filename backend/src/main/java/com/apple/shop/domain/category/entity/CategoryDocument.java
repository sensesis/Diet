package com.apple.shop.domain.category.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDocument {

    private Long id;
    private String name;

    public static CategoryDocument from(Category category) {
        if (category == null) { return null; }


        return CategoryDocument.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
