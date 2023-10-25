package com.example.pirosquare_spring.api;

import com.example.pirosquare_spring.controller.form.PostForm;
import com.example.pirosquare_spring.domain.Post;
import com.example.pirosquare_spring.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    // 게시판 메인
    @GetMapping("/post/api/get_all")
    public List<Post> getAll() {
        return postService.findPosts();
    }

    @GetMapping("/post/api/search")
    public List<Post> search(
            @RequestParam("keyword") String keyword
    ) {
        return postService.search(keyword);
    }

    @GetMapping("/post/api/filtering")
    public List<Post> filtering(
            @RequestParam("filter") String filter
    ) {
        return postService.filtering(filter);
    }

    @GetMapping("/post/api/detail/{postId}")
    public Post detail(@PathVariable("postId") Long postId) {
        return postService.findOne(postId);
    }

    @PostMapping("/post/api/create")
    public Long create(
            @RequestParam("userId") Long userId,
            @RequestParam("title") String title,
            @RequestParam("content") String content
    ) {
        return postService.create(userId, title, content);
    }

    @PostMapping("/post/api/update/{postId}")
    public Long updatePost(@PathVariable Long postId, @Valid PostForm form, BindingResult result) {
        if (result.hasErrors()) {
            return (long) -1;
        }

        return postService.update(postId, form.getTitle(), form.getContent());
    }

    @PostMapping("/post/api/delete/{postId}")
    public Long deletePost(@PathVariable Long postId){
        return postService.delete(postId);
    }
}
