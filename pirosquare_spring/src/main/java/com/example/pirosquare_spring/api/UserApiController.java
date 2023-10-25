package com.example.pirosquare_spring.api;

import com.example.pirosquare_spring.controller.form.UserForm;
import com.example.pirosquare_spring.domain.User;
import com.example.pirosquare_spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserApiController {
    
    private final UserService userService;

    @GetMapping("/user/api/get_all")
    public List<User> getAll() {
        return userService.findUsers();
    }

    @GetMapping("/user/api/detail/{userId}")
    public User detail(@PathVariable("userId") Long userId) {
        return userService.findOne(userId);
    }

    @PostMapping("/user/api/create")
    public Long create(@Valid UserForm form, BindingResult result) {
        if (result.hasErrors()) {
            return (long) -1;
        }
        User user = new User(form.getId(), form.getPassword(), form.getName());

        return userService.create(user);
    }
}
