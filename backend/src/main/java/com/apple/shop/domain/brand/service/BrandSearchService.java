package com.apple.shop.domain.brand.service;

import com.apple.shop.domain.brand.entity.Brand;
import com.apple.shop.domain.brand.entity.BrandDocument;
import com.apple.shop.domain.brand.repository.BrandRepository;
import com.apple.shop.domain.brand.repository.BrandSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandSearchService {

    private final BrandRepository brandRepository; // JPA 레포지토리
    private final BrandSearchRepository brandSearchRepository; // Elasticsearch 레포지토리

    // 전체 데이터베이스에 있는 브랜드 정보를 Elasticsearch에 인덱싱하는 메서드
    @Transactional
    public void indexAllBrands() {

        List<Brand> brands = brandRepository.findAll();

        List<BrandDocument> brandDocuments = brands.stream()
                .map(BrandDocument::from)
                .collect(Collectors.toList());

        brandSearchRepository.saveAll(brandDocuments);
    }

    public void indexBrand(Brand brand) {
        // 브랜드 엔티티를 BrandDocument로 변환합니다.
        BrandDocument brandDocument = BrandDocument.from(brand);

        // Elasticsearch에 저장(인덱싱)합니다.
        brandSearchRepository.save(brandDocument);
    }

    public List<BrandDocument> searchBrandByName(String name) {
        // Elasticsearch 레포지토리에서 브랜드 이름으로 검색합니다.
        return brandSearchRepository.findByName(name);
    }
}
