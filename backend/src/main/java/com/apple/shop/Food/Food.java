package com.apple.shop.Food;

import com.apple.shop.Nutrition.Nutrition;
import com.apple.shop.SubCategory.SubCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "food")
@Getter
@Setter
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "subcategory_id") // 조인 컬럼 이름 수정
    private SubCategory subCategory;

    @OneToOne(mappedBy = "food", cascade = CascadeType.ALL)
    private Nutrition nutrition;
}

