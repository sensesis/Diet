package com.apple.shop.Users;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "User update request")
public class UserUpdateDTO {

    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Email(message = "유효한 이메일 형식을 입력하세요.")
    @Schema(description = "User email", example = "john.doe@example.com")
    private String email;

    @NotBlank(message = "닉네임은 필수 입력 항목입니다.")
    @Size(min = 2, message = "닉네임은 최소 2자 이상이어야 합니다.")
    @Schema(description = "User name", example = "John Doe")
    private String name;

    // 비밀번호를 업데이트할 필요가 없다면 아래 필드를 제거합니다.
    /*
    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    @Schema(description = "User password", example = "SecurePass123!")
    private String password;
    */

    @Schema(description = "User height in centimeters", example = "175.5")
    private Double height;

    @Schema(description = "User weight in kilograms", example = "70.0")
    private Double weight;

    // bodyFat은 서비스 로직에서 계산되므로 DTO에 포함할 필요가 없습니다.
    /*
    @Schema(description = "User body fat percentage", example = "15.5")
    private Double bodyFat;
    */

    @Schema(description = "User age", example = "30")
    private Integer age; // int에서 Integer로 변경하여 선택적으로 만듭니다.

    @Schema(description = "User gender", example = "Male")
    private String gender;
}