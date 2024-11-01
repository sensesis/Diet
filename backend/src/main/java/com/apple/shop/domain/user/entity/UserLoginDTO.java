package com.apple.shop.domain.user.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "User log-in request")
public class UserLoginDTO {


    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Email(message = "유효한 이메일 형식을 입력하세요.")
    @Schema(description = "User email", example = "john.doe@example.com")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Size(min = 8, message = "비밀번호는 최소 2자 이상이어야 합니다.")
//    @Pattern(
//            regexp = "^(?=.*[a-z])(?=.*\\d)[a-z\\d]{8,}$",
//            message = "비밀번호는 최소 8자 이상이어야 하며, 하나 이상의 소문자와 숫자를 포함해야 합니다."
//    )
    @Schema(description = "User password", example = "SecurePass123!")
    private String password;

}
