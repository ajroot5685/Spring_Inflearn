package com.example.pirosquare_spring.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class PostForm {

    private Long id;
    @NotEmpty(message = "글 제목은 필수입니다.")
    private String title;
    private String content;
}
