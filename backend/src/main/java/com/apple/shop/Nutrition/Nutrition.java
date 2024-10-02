package com.apple.shop.Nutrition;

import com.apple.shop.Food.Food;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "nutrition")
@Getter
@Setter
public class Nutrition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double calorie;
    private double carbohydrate;
    private double sugar;
    private double fat;
    private double protein;
    private double sodium;
    private double saturatedFattyAcids;
    private double cholesterol;

    @OneToOne
    @JoinColumn(name = "food_id") // 조인 컬럼 이름 확인
    private Food food;
}
