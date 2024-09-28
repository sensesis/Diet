package com.apple.shop.Users;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;


@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

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
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDTO userDTO) {

        User user = userRepository.findByEmail(userDTO.getEmail())
                .orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
        }

        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setHeight(user.getHeight());
        user.setWeight(user.getWeight());

        if (user.getHeight() != null && user.getWeight() != null) {

            double heightInMeters = user.getHeight() / 100.0;
            double bmi = user.getWeight() / (heightInMeters * heightInMeters);
            int genderFactor = user.getGender().equalsIgnoreCase("male") ? 1 : 0;

            user.setBody_Fat(1.2 * bmi + 0.23 * user.getAge() - 10.8 * genderFactor - 5.4);
        }

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    public ResponseEntity<?> deleteUser(@Valid @RequestBody UserDTO userDTO) {

        User user = userRepository.findByEmail(userDTO.getEmail()).orElse(null);

        userRepository.delete(user);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
