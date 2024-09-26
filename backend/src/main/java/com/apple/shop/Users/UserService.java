package com.apple.shop.Users;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

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

    public ResponseEntity<?> loginUser(@Valid @RequestBody UserDTO userDTO) {

        User user = userRepository.findByEmail(userDTO.getEmail()).orElse(null);

        if (user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 이메일을 다시 입력해주세요.");


        if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword()))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호를 다시 입력해주세요.");


        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
