package com.apple.shop.SubCategory;

import com.apple.shop.Brand.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    Optional<SubCategory> findByNameAndBrand(String name, Brand brand);
}
