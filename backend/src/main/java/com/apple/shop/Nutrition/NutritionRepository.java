package com.apple.shop.Nutrition;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NutritionRepository extends JpaRepository<Nutrition, Long> {

    Optional<Nutrition> findById(Long id);
}
