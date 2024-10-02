package com.apple.shop.SubCategory;

import com.apple.shop.Brand.Brand;
import com.apple.shop.Food.Food;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subcategory")
@Getter
@Setter
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id") // 조인 컬럼 이름 확인
    private Brand brand;

    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL)
    private List<Food> foods = new ArrayList<>();
}

