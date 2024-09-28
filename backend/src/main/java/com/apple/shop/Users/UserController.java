package com.apple.shop.Users;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Validated
@Tag(name = "User", description = "User API") // "Member"에서 "User"로 변경
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    @Operation(summary = "Sign Up User", description = "새로운 사용자를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "사용자가 성공적으로 등록되었습니다.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "409", description = "이미 사용 중인 이메일입니다.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.",
                    content = @Content)
    })
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDTO userDTO) {

        return userService.signupUser(userDTO);
    }

    // 로그인 API
    @PostMapping("/login")
    @Operation(summary = "Sign in User", description = "사용자가 로그인을 합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "인증 실패",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.",
                    content = @Content)
    })
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginDTO userLoginDTO) {

        return userService.loginUser(userLoginDTO);
    }

    // 사용자 삭제 API
    @PostMapping("/logout")
    @Operation(summary = "Logout User", description = "사용자 정보를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그아웃 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "로그아웃 실패",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.",
                    content = @Content)
    })
    public ResponseEntity<?> LogoutUser(@Valid @RequestBody UserDTO userDTO) {

        return userService.logoutUser(userDTO);
    }

    // 사용자 정보수정 API
    @PostMapping("/update")
    @Operation(summary = "Update User", description = "사용자 정보를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "수정 실패",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.",
                    content = @Content)
    })
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDTO userDTO) {

        return userService.updateUser(userDTO);
    }

    // 사용자 삭제 API
    @PostMapping("/delete")
    @PreAuthorize("#userDTO.email == authentication.name") // 인증된 사용자만 자신 계정 삭제
    @Operation(summary = "Update User", description = "사용자 정보를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "삭제 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "삭제 실패",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.",
                    content = @Content)
    })
    public ResponseEntity<?> DeleteUser(@Valid @RequestBody UserDTO userDTO) {

        return userService.deleteUser(userDTO);
    }
}
