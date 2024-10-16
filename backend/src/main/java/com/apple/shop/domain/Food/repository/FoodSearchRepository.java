package com.apple.shop.domain.Food.repository;

import com.apple.shop.domain.Food.entity.FoodDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodSearchRepository extends ElasticsearchRepository<FoodDocument, Long> {

    List<FoodDocument> findByNameOrBrand(String name, String brand); // 'brand'로 수정
}
