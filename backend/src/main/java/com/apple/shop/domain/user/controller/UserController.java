package com.apple.shop.domain.user.controller;

import com.apple.shop.domain.user.entity.UserDTO;
import com.apple.shop.domain.user.entity.UserLoginDTO;
import com.apple.shop.domain.user.service.UserService;
import com.apple.shop.domain.user.entity.UserUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Validated
@Tag(name = "User API", description = "유저 API")
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "새로운 사용자를 등록합니다.")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDTO userDTO) {

        return userService.signupUser(userDTO);
    }

    // 로그인 API
    @PostMapping("/login")
    @Operation(summary = "로그인", description = "사용자가 로그인을 합니다.")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginDTO userLoginDTO) {

        return userService.loginUser(userLoginDTO);
    }

    // 사용자 로그아웃 API
    @PostMapping("/logout")
    @Operation(summary = "로그아웃", description = "사용자가 로그아웃을 합니다.")
    public ResponseEntity<?> LogoutUser(@Valid @RequestBody UserDTO userDTO) {

        return userService.logoutUser(userDTO);
    }

    // 사용자 정보수정 API
    @PutMapping("/update")
    @Operation(summary = "사용자 수정", description = "사용자 정보를 수정합니다.")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        return userService.updateUser(userUpdateDTO);
    }

    // 사용자 삭제 API
    @DeleteMapping("/delete")
    @Operation(summary = "사용자 삭제", description = "사용자 정보를 삭제합니다")
    @PreAuthorize("#userDTO.email == authentication.name") // 인증된 사용자만 자신 계정 삭제
    public ResponseEntity<?> DeleteUser(@Valid @RequestBody UserDTO userDTO) {
        return userService.deleteUser(userDTO);
    }

    // 사용자 조회 API
    @GetMapping("/inquiry")
    @Operation(summary = "사용자 조회", description = "사용자 정보를 조회합니다")
    public ResponseEntity<?> InquiryUser(@RequestParam("email") @Valid @Email(message = "유효한 이메일 형식을 입력하세요.") String email) {
        return userService.inquiryUser(email);
    }
}
