package com.example.pirosquare_spring.controller.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class UserForm {

    private Long uid;
    @NotEmpty(message = "아이디는 필수입니다.")
    private String id;
    private String password;
    private String name;
}
