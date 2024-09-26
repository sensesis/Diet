package com.apple.shop.cream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 설정파일, 정의할 때 쓰는 어노테이션
@EnableWebSecurity // Spring Security 활성 어노테이션 -> 보안 적용
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable()); // crsf 기능 끄기, 배포 전에는 귀찮음.

        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ); // 로그인 시, 세션 데이터 생성 x란 뜻

        http.authorizeHttpRequests((authorize) ->
                authorize.requestMatchers("/**").permitAll() // 모든 요청 경로 인증없이 허용
        );


        return http.build();
    }

}