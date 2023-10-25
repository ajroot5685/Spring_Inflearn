package com.example.pirosquare_spring.controller;

import com.example.pirosquare_spring.controller.form.UserForm;
import com.example.pirosquare_spring.domain.User;
import com.example.pirosquare_spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/new")
    private String createForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "users/createUserForm";
    }

    @PostMapping("/user/new")
    public String create(@Valid UserForm form, BindingResult result){
        if (result.hasErrors()) {
            System.out.println(result);
            return "users/createUserForm";
        }

        User user = new User(form.getId(), form.getPassword(), form. getName());

        userService.create(user);

        return "redirect:/user";
    }

    @GetMapping("/user")
    public String list(Model model){
        List<User> users = userService.findUsers();
        model.addAttribute("users", users);
        return "users/userList";
    }
}
