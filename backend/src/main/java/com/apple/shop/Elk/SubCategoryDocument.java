package com.apple.shop.Elk;

import com.apple.shop.Food.Food;
import com.apple.shop.SubCategory.SubCategory;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;
import java.util.stream.Collectors;

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
