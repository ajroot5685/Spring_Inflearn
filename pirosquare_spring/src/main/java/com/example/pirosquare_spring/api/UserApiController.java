package com.example.pirosquare_spring.api;

import com.example.pirosquare_spring.controller.form.UserForm;
import com.example.pirosquare_spring.domain.Post;
import com.example.pirosquare_spring.domain.User;
import com.example.pirosquare_spring.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserApiController {
    
    private final UserService userService;

//    @GetMapping("/user/api/get_all")
//    public List<User> getAll() {
//        return userService.findUsers();
//    }

    // 페이징 기능, 엔티티 노출 방지, 성능 최적화된 getAll()
    @GetMapping("/user/api/get_all")
    public List<UserDto> getAll(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "100") int limit
    ){
        List<User> users = userService.findUsersWithPosts(offset, limit);

        List<UserDto> result = users.stream()
                .map(u -> new UserDto(u))
                .collect(Collectors.toList());

        return result;
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

    @Getter
    static class UserDto{
        private Long uid;
        private String id;
//        private String password;
        // password는 노출시키지 않는다고 가정
        private String name;
        private LocalDateTime createtime;
        private List<UserPostDto> posts;

        public UserDto(User user) {
            uid = user.getUid();
            id = user.getId();
            name = user.getName();
            createtime = user.getCreatetime();
            posts = user.getPosts().stream()
                    .map(post -> new UserPostDto(post))
                    .collect(Collectors.toList());
        }
    }

    @Getter
    static class UserPostDto{
        // UserPostDto에서는 3개 필드만 사용
        private Long id;
        private String title;
        private String content;

        public UserPostDto(Post post) {
            id = post.getId();
            title = post.getTitle();
            content = post.getContent();
        }
    }
}
