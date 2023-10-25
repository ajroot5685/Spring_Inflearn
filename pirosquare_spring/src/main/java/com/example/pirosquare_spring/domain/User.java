package com.example.pirosquare_spring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long uid;

    private String id;
    private String password;
    private String name;
    private LocalDateTime createtime;

    public User() {
    }

    public User(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
        createtime = LocalDateTime.now();
    }
}
