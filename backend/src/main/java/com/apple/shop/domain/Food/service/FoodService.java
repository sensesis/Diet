package com.apple.shop.domain.Food.service;

import com.apple.shop.domain.Food.entity.Food;
import com.apple.shop.domain.Food.entity.FoodDocument;
import com.apple.shop.domain.Food.entity.FoodRequestDTO;
import com.apple.shop.domain.Food.repository.FoodRepository;
import com.apple.shop.domain.Food.repository.FoodSearchRepository;
import com.apple.shop.domain.brand.entity.Brand;
import com.apple.shop.domain.brand.repository.BrandRepository;
import com.apple.shop.domain.category.entity.Category;
import com.apple.shop.domain.category.repository.CategoryRepository;
import com.apple.shop.domain.nutrition.entity.Nutrition;
import com.apple.shop.domain.subCategory.entity.SubCategory;
import com.apple.shop.domain.subCategory.repository.SubCategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FoodService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodSearchRepository foodSearchRepository;

    @Transactional
    public Food createFood(FoodRequestDTO requestDTO) {
        // 1. 카테고리 처리
        Category category = categoryRepository.findByName(requestDTO.getCategory())
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(requestDTO.getCategory());
                    return categoryRepository.save(newCategory);
                });

        // 2. 브랜드 처리
        Brand brand = brandRepository.findByNameAndCategory(requestDTO.getBrand(), category)
                .orElseGet(() -> {
                    Brand newBrand = new Brand();
                    newBrand.setName(requestDTO.getBrand());
                    newBrand.setCategory(category);
                    return brandRepository.save(newBrand);
                });

        // 3. 서브 카테고리 처리
        SubCategory subCategory = subCategoryRepository.findByNameAndBrand(requestDTO.getSubCategory(), brand)
                .orElseGet(() -> {
                    SubCategory newSubCategory = new SubCategory();
                    newSubCategory.setName(requestDTO.getSubCategory());
                    newSubCategory.setBrand(brand);
                    return subCategoryRepository.save(newSubCategory);
                });

        // 4. 음식 및 영양성분 처리
        FoodRequestDTO.FoodDTO foodDTO = requestDTO.getFood();
        Food food = new Food();
        food.setName(foodDTO.getName());
        food.setSubCategory(subCategory);
        food.setBrand(brand); // Brand 설정 추가

        // 영양성분 설정
        FoodRequestDTO.NutritionDTO nutritionDTO = foodDTO.getNutrition();
        Nutrition nutrition = new Nutrition();
        nutrition.setCalorie(nutritionDTO.getCalorie());
        nutrition.setCarbohydrate(nutritionDTO.getCarbohydrate());
        nutrition.setSugar(nutritionDTO.getSugar());
        nutrition.setFat(nutritionDTO.getFat());
        nutrition.setProtein(nutritionDTO.getProtein());
        nutrition.setSodium(nutritionDTO.getSodium());
        nutrition.setSaturatedFattyAcids(nutritionDTO.getSaturatedFattyAcids());
        nutrition.setCholesterol(nutritionDTO.getCholesterol());

        // 연관관계 설정
        nutrition.setFood(food);
        food.setNutrition(nutrition);

        // 음식 저장 (영양성분은 Cascade 옵션으로 자동 저장)
        Food savedFood = foodRepository.save(food);

        // Elasticsearch에 저장
        FoodDocument foodDocument = FoodDocument.from(savedFood);
        foodSearchRepository.save(foodDocument);

        return savedFood;
    }

    @Transactional
    public Food updateFood(Long id, FoodRequestDTO requestDTO) {
        // 1. 기존 음식 찾기
        Food existingFood = foodRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Food not found with id: " + id));

        // 2. 카테고리 처리
        Category category = categoryRepository.findByName(requestDTO.getCategory())
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(requestDTO.getCategory());
                    return categoryRepository.save(newCategory);
                });

        // 3. 브랜드 처리
        Brand brand = brandRepository.findByNameAndCategory(requestDTO.getBrand(), category)
                .orElseGet(() -> {
                    Brand newBrand = new Brand();
                    newBrand.setName(requestDTO.getBrand());
                    newBrand.setCategory(category);
                    return brandRepository.save(newBrand);
                });

        // 4. 서브 카테고리 처리
        SubCategory subCategory = subCategoryRepository.findByNameAndBrand(requestDTO.getSubCategory(), brand)
                .orElseGet(() -> {
                    SubCategory newSubCategory = new SubCategory();
                    newSubCategory.setName(requestDTO.getSubCategory());
                    newSubCategory.setBrand(brand);
                    return subCategoryRepository.save(newSubCategory);
                });

        // 5. 음식 정보 업데이트
        existingFood.setName(requestDTO.getFood().getName());
        existingFood.setSubCategory(subCategory);
        existingFood.setBrand(brand); // Brand 설정 업데이트

        // 6. 영양성분 업데이트
        FoodRequestDTO.NutritionDTO nutritionDTO = requestDTO.getFood().getNutrition();
        Nutrition nutrition = existingFood.getNutrition();
        if (nutrition == null) {
            nutrition = new Nutrition();
            nutrition.setFood(existingFood);
            existingFood.setNutrition(nutrition);
        }
        nutrition.setCalorie(nutritionDTO.getCalorie());
        nutrition.setCarbohydrate(nutritionDTO.getCarbohydrate());
        nutrition.setSugar(nutritionDTO.getSugar());
        nutrition.setFat(nutritionDTO.getFat());
        nutrition.setProtein(nutritionDTO.getProtein());
        nutrition.setSodium(nutritionDTO.getSodium());
        nutrition.setSaturatedFattyAcids(nutritionDTO.getSaturatedFattyAcids());
        nutrition.setCholesterol(nutritionDTO.getCholesterol());

        // 7. 음식 저장 (Cascade 옵션으로 Nutrition도 자동 저장)
        Food updatedFood = foodRepository.save(existingFood);

        // 8. Elasticsearch 인덱스 업데이트
        FoodDocument foodDocument = FoodDocument.from(updatedFood);
        foodSearchRepository.save(foodDocument);

        return updatedFood;
    }

    @Transactional
    public void deleteFood(Long id) {

        Food existingFood = foodRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Food not found with id: " + id));

        foodSearchRepository.deleteById(id);

        foodRepository.delete(existingFood);
    }
}
