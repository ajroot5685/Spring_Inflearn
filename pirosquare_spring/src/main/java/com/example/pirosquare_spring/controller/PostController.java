package com.example.pirosquare_spring.controller;

import com.example.pirosquare_spring.domain.Post;
import com.example.pirosquare_spring.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/new")
    public String createForm(Model model){
        model.addAttribute("postForm", new PostForm());
        return "posts/createPostForm";
    }

    /*
    * @Valid
    * PostForm 에 설정한 validation 조건들을 검사한다.
    *
    * BindingResult
    * @Valid 뒤에 이 변수를 설정하면 유효성 검사에 실패했을때 오류를 result에 담아준다.
    */
    @PostMapping("/new")
    public String create(@Valid PostForm form, BindingResult result){
        if (result.hasErrors()) {
            return "posts/createPostForm";
        }

        Post post = new Post(form.getTitle(), form.getContent());

        postService.create(post);

        return "redirect:/";
    }

//    @GetMapping("/")
//    public String list(Model model) {
//        List<Post> posts = postService.findPosts();
//        model.addAttribute("posts", posts);
//        return "/posts/postList";
//    }
    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<Post>> list(Model model) {
        List<Post> posts = postService.findPosts();

        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @GetMapping("/{postId}/edit")
    public String update(@PathVariable("postId") Long postId, Model model){
        Post post = postService.findOne(postId);

        PostForm form = new PostForm();
        form.setId(post.getId());
        form.setTitle(post.getTitle());
        form.setContent(post.getContent());

        model.addAttribute("postForm", form);
        return "posts/updatePostForm";
    }

    @PostMapping("/{postId}/edit")
    public String updatePost(@PathVariable Long postId, @ModelAttribute("form") PostForm form){
        postService.update(postId, form.getTitle(), form.getContent());
        return "redirect:/";
    }
}