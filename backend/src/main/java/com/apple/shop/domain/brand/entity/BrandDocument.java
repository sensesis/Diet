package com.apple.shop.domain.brand.entity;

import com.apple.shop.domain.category.entity.CategoryDocument;
import com.apple.shop.domain.subCategory.entity.SubCategoryDocument;
import com.apple.shop.domain.Food.entity.FoodDocument;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@Document(indexName = "brand_index")
public class BrandDocument {

    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Object)
    private CategoryDocument category;

    @Field(type = FieldType.Nested)
    private List<SubCategoryDocument> subCategories;

    @Field(type = FieldType.Nested)
    private List<FoodDocument> foods;

    public static BrandDocument from(Brand brand) {

        if (brand == null) { return null; }

        return BrandDocument.builder()
                .id(brand.getId())
                .name(brand.getName())
                .category(CategoryDocument.from(brand.getCategory()))
                .subCategories(
                        brand.getSubCategories().stream()
                                .map(SubCategoryDocument::from)
                                .collect(Collectors.toList())
                )
                .foods(
                        brand.getFoods().stream()
                                .map(FoodDocument::from)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
