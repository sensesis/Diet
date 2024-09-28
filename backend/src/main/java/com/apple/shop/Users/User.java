package com.apple.shop.Users;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;


    @Column(nullable = false)
    public String name;
    public String password;

    @Column(nullable = false, unique = true)
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
