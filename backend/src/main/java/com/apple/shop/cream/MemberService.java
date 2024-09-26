package com.apple.shop.cream;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;  // 오타 수정
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void createUser(String email, String password) throws Exception {

        var result = memberRepository.findByEmail(email);

        if (email.length() < 8 || password.length() < 8)
            throw new Exception("너무 짧음 ㅇㅇ..");


        if (result.isPresent())
            throw new Exception("존재함 ㅋㅋ");

        var encoder = passwordEncoder;
        Member member = new Member();

        member.setEmail(email);
        member.setPassword(encoder.encode(password));

        memberRepository.save(member);  // 여기도 올바르게 수정
    }
}
