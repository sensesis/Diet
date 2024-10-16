package com.apple.shop.domain.Food.controller;

import com.apple.shop.domain.Food.entity.Food;
import com.apple.shop.domain.Food.entity.FoodDocument;
import com.apple.shop.domain.Food.entity.FoodRequestDTO;
import com.apple.shop.domain.Food.entity.FoodResponseDTO;
import com.apple.shop.domain.Food.service.FoodSearchService;
import com.apple.shop.domain.Food.service.FoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/foods")
@Tag(name = "Food API", description = "음식 정보 API")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private FoodSearchService foodSearchService;

    @PostMapping
    @Operation(summary = "음식 등록", description = "새로운 음식을 등록합니다.")
    public ResponseEntity<FoodResponseDTO> createFood(@RequestBody FoodRequestDTO requestDTO) {
        Food savedFood = foodService.createFood(requestDTO);

        // FoodResponseDTO의 from 메서드를 사용하여 매핑
        FoodResponseDTO responseDTO = FoodResponseDTO.from(savedFood);

        // 생성된 리소스의 URI를 Location 헤더에 추가
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedFood.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(responseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "음식 업데이트", description = "기존 음식을 업데이트합니다.")
    public ResponseEntity<FoodResponseDTO> updateFood(@PathVariable Long id, @RequestBody FoodRequestDTO requestDTO) {
        Food updatedFood = foodService.updateFood(id, requestDTO);

        // FoodResponseDTO의 from 메서드를 사용하여 매핑
        FoodResponseDTO responseDTO = FoodResponseDTO.from(updatedFood);

        // 생성된 리소스의 URI를 Location 헤더에 추가
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(updatedFood.getId())
                .toUri();

        return ResponseEntity.ok()
                .header("Location", location.toString())
                .body(responseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "음식 삭제", description = "기존의 음식을 삭제합니다.")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        foodService.deleteFood(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "음식 검색", description = "이름 또는 브랜드로 음식을 검색합니다.")
    public ResponseEntity<List<FoodDocument>> searchNameOrBrand(@RequestParam String keyword) {
        try {
            List<FoodDocument> results = foodSearchService.search(keyword);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            // 구체적인 에러 로그를 추가
            e.printStackTrace();
            return ResponseEntity.status(500).body(Collections.emptyList());
        }
    }
}
