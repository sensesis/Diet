package com.apple.shop.domain.subCategory.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Builder
public class SubCategoryDocument {

    @Field(type = FieldType.Long)
    private Long id;

    @Field(type = FieldType.Text)
    private String name;

    public static SubCategoryDocument from(SubCategory subCategory) {

        if (subCategory == null) { return null; }

        return SubCategoryDocument.builder()
                .id(subCategory.getId())
                .name(subCategory.getName())
                .build();
    }
}
