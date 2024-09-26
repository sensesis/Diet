package com.apple.shop.Util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Spring 구성 클래승미 나타냄. Spring Security 설정 포함
@EnableWebSecurity // Spring Security를 활성화 시킴
public class SecurityConfig { //6버전 이상 쓰는 문법



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf((csrf) -> csrf.disable());

        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ); // session 데이터 생성하지 말라는 코드

        http.authorizeHttpRequests((authorize) ->
                authorize.requestMatchers("/**").permitAll());

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //filterchain은 유저 요청 + 서버 응답사이 자동으로 실행해주고 싶은 코드 담는 곳, 미들웨어라고함
}