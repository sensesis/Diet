package com.apple.shop.domain.user.service;

import com.apple.shop.domain.user.entity.UserDTO;
import com.apple.shop.domain.user.entity.UserLoginDTO;
import com.apple.shop.domain.user.repository.UserRepository;
import com.apple.shop.domain.user.entity.UserUpdateDTO;
import com.apple.shop.domain.user.controller.UserController;
import com.apple.shop.domain.user.entity.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    // 회원가입 Service
    public ResponseEntity<?> signupUser(@Valid @RequestBody UserDTO userDTO) {

        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {

            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("이미 사용 중인 이메일입니다.");
        }

        var hash = passwordEncoder.encode(userDTO.getPassword());

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(hash);
        user.setName(userDTO.getName());
        user.setRegistrationDate(LocalDateTime.now());
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("사용자가 성공적으로 등록되었습니다.");
    }

    // 로그인 Service
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginDTO userLoginDTO) {

        User user = userRepository.findByEmail(userLoginDTO.getEmail()).orElse(null);

        if (user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 이메일을 다시 입력해주세요.");


        if (!passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword()))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호를 다시 입력해주세요.");


        user.setLastLoginDate(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    // 로그아웃 Service
    public ResponseEntity<?> logoutUser(@Valid @RequestBody UserDTO userDTO) {

        User user = userRepository.findByEmail(userDTO.getEmail()).orElse(null);

        if (user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 정보가 없습니다.");


        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    // 수정 Service
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserUpdateDTO userUpdateDTO) {

        User user = userRepository.findByEmail(userUpdateDTO.getEmail())
                .orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
        }

        user.setEmail(userUpdateDTO.getEmail());
        user.setName(userUpdateDTO.getName());

        // height과 weight 업데이트
        user.setHeight(userUpdateDTO.getHeight());
        user.setWeight(userUpdateDTO.getWeight());

        // age과 gender 업데이트 (선택적)
        if (userUpdateDTO.getAge() != null) {
            user.setAge(userUpdateDTO.getAge());
        }

        if (userUpdateDTO.getGender() != null && !userUpdateDTO.getGender().isEmpty()) {
            user.setGender(userUpdateDTO.getGender());
        }

        // BMI 및 체지방률 계산
        if (user.getHeight() != null && user.getWeight() != null && user.getAge() != 0 && user.getGender() != null) {

            double heightInMeters = user.getHeight() / 100.0;
            double bmi = user.getWeight() / (heightInMeters * heightInMeters);
            int genderFactor = user.getGender().equalsIgnoreCase("male") ? 1 : 0;

            user.setBody_Fat(1.2 * bmi + 0.23 * user.getAge() - 10.8 * genderFactor - 5.4);
        }

        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    // 삭제 Service
    public ResponseEntity<?> deleteUser(@Valid @RequestBody UserDTO userDTO) {

        User user = userRepository.findByEmail(userDTO.getEmail()).orElse(null);

        userRepository.delete(user);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    // 사용자 조회 메서드
    public ResponseEntity<?> inquiryUser(@RequestParam("email") @Valid @Email(message = "유효한 이메일 형식을 입력하세요.") String email) {

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
    }
}
