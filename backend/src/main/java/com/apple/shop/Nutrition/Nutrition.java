package com.apple.shop.Nutrition;

import com.apple.shop.Food.Food;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "nutrition")
@Entity
public class Nutrition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double calorie;
    private Double carbohydrate;
    private Double sugar;
    private Double fat;
    private Double protein;
    private Double sodium;
    private Double saturatedFattyAcids;
    private Double cholesterol;

    @OneToOne
    @JoinColumn(name = "food_id")
    private Food food;
}
