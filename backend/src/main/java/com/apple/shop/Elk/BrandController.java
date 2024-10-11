package com.apple.shop.Elk;

import com.apple.shop.Brand.Brand;
import com.apple.shop.Brand.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@Tag(name = "Search API", description = "음식 검색 API")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;
    private final BrandSearchService brandSearchService;

    @PostMapping("/index-all")
    @Operation(summary = "전체 인덱스 등록", description = "전체 인덱스를 등록합니다.")
    public ResponseEntity<String> indexAllBrands() {
        try {
            brandSearchService.indexAllBrands();
            return ResponseEntity.ok("Indexing completed successfully.");
        } catch (Exception e) {
            // 로그를 추가하거나 예외 처리를 상세히 할 수 있습니다.
            return ResponseEntity.status(500).body("An error occurred during indexing.");
        }
    }

    @PostMapping("/add")
    @Operation(summary = "개별 인덱스 등록", description = "개별 인덱스를 등록합니다.")
    public ResponseEntity<Brand> addBrand(@RequestBody Brand brand) {
        // 데이터베이스에 브랜드를 저장하고 Elasticsearch에 인덱싱합니다.
        Brand savedBrand = brandService.addBrand(brand);
        brandSearchService.indexBrand(savedBrand);
        return ResponseEntity.ok(savedBrand);
    }

    @GetMapping("/search")
    @Operation(summary = "브랜드 검색", description = "브랜드를 검색합니다.")
    public ResponseEntity<List<BrandDocument>> searchBrands(@RequestParam String name) {
        List<BrandDocument> results = brandSearchService.searchBrandByName(name);
        return ResponseEntity.ok(results);
    }
}
