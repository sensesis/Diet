package com.apple.shop.Brand;

import com.apple.shop.Brand.Brand;
import com.apple.shop.Category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findByNameAndCategory(String name, Category category);
}