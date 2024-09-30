package com.apple.shop.Users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "User response")
public class UserResponseDTO {

    @Schema(description = "User ID", example = "1")
    private Long id;

    @Schema(description = "User name", example = "John Doe")
    private String name;

    @Schema(description = "User email", example = "john.doe@example.com")
    private String email;

    @Schema(description = "User height in centimeters", example = "175.5")
    private Double height;

    @Schema(description = "User weight in kilograms", example = "70.0")
    private Double weight;

    @Schema(description = "User body fat percentage", example = "15.5")
    private Double bodyFat;

    @Schema(description = "User age", example = "30")
    private int age;

    @Schema(description = "User gender", example = "Male")
    private String gender;

    @Schema(description = "User status", example = "true")
    private Boolean status;

    @Schema(description = "User image URL", example = "http://example.com/image.jpg")
    private String imageUrl;

    @Schema(description = "Registration date", example = "2023-10-01T12:34:56")
    private String registrationDate;

    @Schema(description = "Last login date", example = "2024-04-15T08:22:10")
    private String lastLoginDate;

    @Schema(description = "Updated at date", example = "2024-04-15T08:22:10")
    private String updatedAt;
}
