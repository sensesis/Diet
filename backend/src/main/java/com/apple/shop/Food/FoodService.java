package com.apple.shop.Food;

import com.apple.shop.Brand.Brand;
import com.apple.shop.Brand.BrandRepository;
import com.apple.shop.Category.Category;
import com.apple.shop.Category.CategoryRepository;
import com.apple.shop.Nutrition.Nutrition;
import com.apple.shop.SubCategory.SubCategory;
import com.apple.shop.SubCategory.SubCategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Transactional
    public void createFood(FoodRequestDTO requestDTO) {
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
        foodRepository.save(food);
    }
}
