package com.apple.shop.domain.brand.service;

import com.apple.shop.domain.brand.entity.Brand;
import com.apple.shop.domain.brand.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    @Transactional
    public Brand addBrand(Brand brand) {

        return brandRepository.save(brand);
    }
}
