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
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;
    private LocalDateTime createtime;
    private LocalDateTime updatetime;

    public Post() {
    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        createtime=LocalDateTime.now();
        updatetime=LocalDateTime.now();
    }
}
