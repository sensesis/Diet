package com.apple.shop.cream;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@ToString
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 데이터베이스 생성될 때 자동 id 생성
    private Integer id;

    @Column(unique = true, nullable = false)
    private String email;
    private String password;
}



