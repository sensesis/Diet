package com.apple.shop.Food;

import com.apple.shop.Nutrition.Nutrition;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "음식 등록 요청 데이터 모델")
public class FoodRequestDTO {

    @Schema(description = "카테고리 이름", example = "패스트푸드")
    private String category;

    @Schema(description = "브랜드 이름", example = "맘스터치")
    private String brand;

    @Schema(description = "서브 카테고리 이름", example = "햄버거")
    private String subCategory;

    private FoodDTO food;


    @Getter
    @Setter
    @Schema(description = "음식 정보")
    public static class FoodDTO {

        @Schema(description = "음식", example = "싸이버거")
        private String name;
        private NutritionDTO nutrition;
    }

    @Getter
    @Setter
    @Schema(description = "영양성분 정보")
    public static class NutritionDTO {

        @Schema(description = "칼로리", example = "500")
        private double calorie;
        private double carbohydrate;
        private double sugar;
        private double fat;
        private double protein;
        private double sodium;
        private double saturatedFattyAcids;
        private double cholesterol;
    }
}
