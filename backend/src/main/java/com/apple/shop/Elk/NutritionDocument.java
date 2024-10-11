package com.apple.shop.Elk;

import com.apple.shop.Nutrition.Nutrition;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NutritionDocument {

   private Long id;

    private double calorie; // 칼로리
    private double carbohydrate; // 탄수화물
    private double sugar; // 당류
    private double fat; // 지방
    private double protein; // 단백질
    private double sodium; // 나트륨
    private double saturatedFattyAcids; // 포화지방산
    private double cholesterol; // 콜레스테롤

    // Nutrition 엔티티를 NutritionDocument로 변환하는 메서드

    public static NutritionDocument from(Nutrition nutrition) {

        if (nutrition == null) { return null; }

        return NutritionDocument.builder()
                .id(nutrition.getId())
                .calorie(nutrition.getCalorie())
                .carbohydrate(nutrition.getCarbohydrate())
                .sugar(nutrition.getSugar())
                .fat(nutrition.getFat())
                .protein(nutrition.getProtein())
                .sodium(nutrition.getSodium())
                .saturatedFattyAcids(nutrition.getSaturatedFattyAcids())
                .cholesterol(nutrition.getCholesterol())
                .build();
    }
}
