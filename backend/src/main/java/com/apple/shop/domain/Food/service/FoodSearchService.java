package com.apple.shop.domain.Food.service;

import com.apple.shop.domain.Food.entity.FoodDocument;
import com.apple.shop.domain.Food.repository.FoodSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodSearchService {

    private final FoodSearchRepository foodSearchRepository;

    public List<FoodDocument> search(String keyword) {

        return foodSearchRepository.findByNameOrBrand(keyword, keyword);
    }
}
