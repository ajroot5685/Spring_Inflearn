package com.example.pirosquare_spring.controller;

import com.example.pirosquare_spring.controller.form.PostForm;
import com.example.pirosquare_spring.domain.Post;
import com.example.pirosquare_spring.domain.User;
import com.example.pirosquare_spring.service.PostService;
import com.example.pirosquare_spring.service.UserService;
import lombok.RequiredArgsConstructor;
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
    private final UserService userService;

    @GetMapping("/post/new")
    public String createForm(Model model){

        List<User> users = userService.findUsers();

        model.addAttribute("users", users);
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
    @PostMapping("/post/new")
    public String create(
            @RequestParam("userId") Long userId,
            @RequestParam("title") String title,
            @RequestParam("content") String content
    ){
        postService.create(userId, title, content);

        return "redirect:/post";
    }

    @GetMapping("/post")
    public String list(Model model) {
        List<Post> posts = postService.findPosts();
        model.addAttribute("posts", posts);
        return "/posts/postList";
    }
//    @GetMapping("/")
//    @ResponseBody
//    public ResponseEntity<List<Post>> list(Model model) {
//        List<Post> posts = postService.findPosts();
//
//        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
//    }

    @GetMapping("/post/{postId}/edit")
    public String update(@PathVariable("postId") Long postId, Model model){
        Post post = postService.findOne(postId);

        PostForm form = new PostForm();
        form.setId(post.getId());
        form.setTitle(post.getTitle());
        form.setContent(post.getContent());

        model.addAttribute("postForm", form);
        return "posts/updatePostForm";
    }

    @PostMapping("/post/{postId}/edit")
    public String updatePost(@PathVariable Long postId, @ModelAttribute("form") PostForm form){
        postService.update(postId, form.getTitle(), form.getContent());
        return "redirect:/";
    }
}