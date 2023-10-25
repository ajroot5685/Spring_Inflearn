package com.example.pirosquare_spring.api;

import com.example.pirosquare_spring.controller.PostForm;
import com.example.pirosquare_spring.domain.Post;
import com.example.pirosquare_spring.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostRepository postRepository;

    // 게시판 메인
    @GetMapping("/post/api/get_all")
    public List<Post> getAll() {
        List<Post> all = postRepository.findAll();
        return all;
    }

    @GetMapping("/post/api/search")
    public List<Post> search(
            @RequestParam("keyword") String keyword
    ) {
        List<Post> result = postRepository.search(keyword);
        return result;
    }

    @GetMapping("/post/api/filtering")
    public List<Post> filtering(
            @RequestParam("filter") String filter
    ){
        List<Post> result = postRepository.filtering(filter);
        return result;
    }

    @GetMapping("/post/api/detail/{postId}")
    public Post detail(@PathVariable("postId") Long postId){
        Post post = postRepository.find(postId);
        return post;
    }

    @Transactional
    @PostMapping("post/api/create")
    public Long create(@Valid PostForm form, BindingResult result){
        if (result.hasErrors()) {
            return (long) -1;
        }
        System.out.println((long)-1);

        Post post = new Post(form.getTitle(), form.getContent());

        postRepository.save(post);

        return post.getId();
    }
}
