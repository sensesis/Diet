package com.apple.shop.Elk;

import com.apple.shop.Food.Food;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Builder
public class FoodDocument {

    @Field(type = FieldType.Long)
    private Long id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Object)
    private NutritionDocument nutrition;

    public static FoodDocument from(Food food) {
        if (food == null) { return null; }

        return FoodDocument.builder()
                .id(food.getId())
                .name(food.getName())
                .nutrition(NutritionDocument.from(food.getNutrition()))
                .build();
    }
}
