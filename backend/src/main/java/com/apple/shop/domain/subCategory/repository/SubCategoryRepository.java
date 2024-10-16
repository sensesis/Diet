package com.apple.shop.domain.subCategory.repository;

import com.apple.shop.domain.brand.entity.Brand;
import com.apple.shop.domain.subCategory.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    Optional<SubCategory> findByNameAndBrand(String name, Brand brand);
}
