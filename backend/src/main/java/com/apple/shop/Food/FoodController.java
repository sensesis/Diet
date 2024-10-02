package com.apple.shop.Food;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/foods")
@Tag(name = "Food API", description = "음식 정보 API")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @PostMapping
    @Operation(summary = "음식 등록", description = "새로운 음식을 등록합니다.")
    public String createFood(@RequestBody FoodRequestDTO requestDTO) {
        foodService.createFood(requestDTO);
        return "음식이 성공적으로 저장되었습니다.";
    }
}
