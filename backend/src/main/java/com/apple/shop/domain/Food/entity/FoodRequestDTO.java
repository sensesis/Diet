package com.apple.shop.domain.Food.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "음식 정보")
    public static class FoodDTO {

        @Schema(description = "음식", example = "싸이버거")
        private String name;

        private NutritionDTO nutrition;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "영양성분 정보")
    public static class NutritionDTO {

        @Schema(description = "칼로리", example = "500")
        private double calorie;

        @Schema(description = "탄수화물", example = "50")
        private double carbohydrate;

        @Schema(description = "당", example = "10")
        private double sugar;

        @Schema(description = "지방", example = "20")
        private double fat;

        @Schema(description = "단백질", example = "25")
        private double protein;

        @Schema(description = "나트륨", example = "800")
        private double sodium;

        @Schema(description = "포화지방산", example = "5")
        private double saturatedFattyAcids;

        @Schema(description = "콜레스테롤", example = "30")
        private double cholesterol;
    }
}
