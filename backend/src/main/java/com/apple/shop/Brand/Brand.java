package com.apple.shop.Brand;

import com.apple.shop.Category.Category;
import com.apple.shop.SubCategory.SubCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "brand")
@Getter
@Setter
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id") // 소문자로 통일
    private Category category;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private List<SubCategory> subCategories = new ArrayList<>();
}
