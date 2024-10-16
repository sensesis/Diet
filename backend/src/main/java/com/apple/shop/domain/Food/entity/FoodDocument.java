package com.apple.shop.domain.Food.entity;

import com.apple.shop.domain.nutrition.entity.NutritionDocument;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "fooddocument") // 소문자로 인덱스 이름 설정
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodDocument {

    @Field(type = FieldType.Long)
    private Long id;

    @Field(type = FieldType.Text, analyzer = "nori")
    private String name;

    @Field(type = FieldType.Text, name = "category")
    private String category;

    @Field(type = FieldType.Text, name = "brand", analyzer = "nori")
    private String brand;

    @Field(type = FieldType.Text, name = "subcategory") // 일관된 소문자 사용
    private String subCategory;

    @Field(type = FieldType.Object)
    private NutritionDocument nutrition;

    public static FoodDocument from(Food food) {
        if (food == null) { return null; }

        String category = null;
        if (food.getSubCategory() != null &&
                food.getSubCategory().getBrand() != null &&
                food.getSubCategory().getBrand().getCategory() != null) {
            category = food.getSubCategory().getBrand().getCategory().getName();
        }

        return FoodDocument.builder()
                .id(food.getId())
                .name(food.getName())
                .category(category)
                .brand(food.getBrand() != null ? food.getBrand().getName() : null)
                .subCategory(food.getSubCategory() != null ? food.getSubCategory().getName() : null)
                .nutrition(NutritionDocument.from(food.getNutrition()))
                .build();
    }
}
