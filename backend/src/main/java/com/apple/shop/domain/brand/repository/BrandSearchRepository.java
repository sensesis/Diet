package com.apple.shop.domain.brand.repository;

import com.apple.shop.domain.brand.entity.BrandDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;

public interface BrandSearchRepository extends ElasticsearchRepository<BrandDocument, Long> {

    // 브랜드 이름으로 검색하는 메서드
    List<BrandDocument> findByName(String name);

    // 브랜드 이름에 특정 문자열이 포함된 도큐먼트를 검색하는 메서드 (대소문자 구분 없음)
    List<BrandDocument> findByNameContainingIgnoreCase(String name);
}
