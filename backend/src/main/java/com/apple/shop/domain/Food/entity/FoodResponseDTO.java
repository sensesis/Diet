package com.apple.shop.domain.Food.entity;

import com.apple.shop.domain.Food.entity.FoodResponseDTO.NutritionDTO;
import com.apple.shop.domain.nutrition.entity.Nutrition;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Data
@Builder
@Schema(description = "음식 등록 응답 데이터 모델")
public class FoodResponseDTO {

    @Schema(description = "음식 ID", example = "1")
    private Long id;

    @Schema(description = "카테고리 이름", example = "패스트푸드")
    private String category;

    @Schema(description = "브랜드 이름", example = "맘스터치")
    private String brand;

    @Schema(description = "서브 카테고리 이름", example = "햄버거")
    private String subCategory;

    @Schema(description = "음식 이름", example = "싸이버거")
    private String name;

    @Schema(description = "영양성분 정보")
    private NutritionDTO nutrition;

    @Schema(description = "생성된 음식의 URL", example = "http://localhost:8080/api/foods/1")
    private String url;

    @Data
    @Builder
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

    public static FoodResponseDTO from(Food food) {
        if (food == null) return null;

        Nutrition nutrition = food.getNutrition();

        return FoodResponseDTO.builder()
                .id(food.getId())
                .category(food.getSubCategory().getBrand().getCategory().getName())
                .brand(food.getBrand().getName())
                .subCategory(food.getSubCategory().getName())
                .name(food.getName())
                .nutrition(NutritionDTO.builder()
                        .calorie(nutrition.getCalorie())
                        .carbohydrate(nutrition.getCarbohydrate())
                        .sugar(nutrition.getSugar())
                        .fat(nutrition.getFat())
                        .protein(nutrition.getProtein())
                        .sodium(nutrition.getSodium())
                        .saturatedFattyAcids(nutrition.getSaturatedFattyAcids())
                        .cholesterol(nutrition.getCholesterol())
                        .build())
                .url(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(food.getId())
                        .toUriString())
                .build();
    }
}
