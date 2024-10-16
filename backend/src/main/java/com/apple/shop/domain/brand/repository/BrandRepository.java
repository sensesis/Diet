package com.apple.shop.domain.brand.repository;

import com.apple.shop.domain.brand.entity.Brand;
import com.apple.shop.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findByNameAndCategory(String name, Category category);
}