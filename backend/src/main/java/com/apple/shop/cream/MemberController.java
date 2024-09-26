package com.apple.shop.cream;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    @GetMapping("/signup")
    public String showUser(String email, String password) {

        return "signup";
    }

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(String email, String password) {

        try {
            memberService.createUser(email, password);
            return ResponseEntity.ok("회원가입 성공");
        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/login")
    public String loginUser() {

        return "login";
    }

    @PostMapping("/login")
    public String loginUser(String email, String password) {


        Optional<Member> memberEmail = memberRepository.findByEmail(email);
        Member member = memberEmail.get();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //Optional<T>는 특정값 있을수도 있고 없을 수도 있는 상황 처리하기 위해서 사용됨.
        //null 방지, 안전하게 다룰 수 있게 해줌

        if (encoder.matches(password, member.getPassword())) {

            return "redirect:cloth";
        }
            return "redirect:login";
    }

    @PostMapping("/login/jwt")
    @ResponseBody
    public String loginJWT(@RequestBody Map<String, String> data) {

        var authToken = new UsernamePasswordAuthenticationToken(data.get("email"), data.get("password"));
        var auth = authenticationManagerBuilder.getObject().authenticate(authToken); //아이디/비번 DB내용과 비교해서 로그인해줌


        SecurityContextHolder.getContext().setAuthentication(auth);
        return "";
    }

//    @GetMapping("/my-page")
//    public String myPage(Authentication auth) {
//
//        return "my-page";
//    }
}
