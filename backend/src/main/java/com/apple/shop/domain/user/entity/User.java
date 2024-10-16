package com.apple.shop.domain.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키의 생성 전략을 정의합니다. IDENTITY는 데이터베이스가 자동으로 증가시키는 방식
    public Long id;


    @Column(nullable = false) //null을 허용하지 않음
    public String name;
    public String password;

    @Column(nullable = false, unique = true) // null을 허용하지 않으며, 고유(unique) 해야 함을 명시합니다
    public String email;

    private LocalDateTime registrationDate; // 오타 수정 및 타입 변경
    private LocalDateTime lastLoginDate;
    private String imageUrl;
    private LocalDateTime updatedAt;
    private int point;
    private Boolean status;

    private Double height; // 타입 변경
    private Double weight; // 타입 변경
    private Double body_Fat; // 타입 변경
    private int age;
    private String gender;
}
