package com.example.pirosquare_spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @JsonIgnore // 이 어노테이션이 없으면 조회할 때 무한 참조가 일어나게 된다.
    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    public User() {
    }

    public User(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
        createtime = LocalDateTime.now();
    }
}
