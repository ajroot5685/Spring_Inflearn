package com.example.pirosquare_spring.service;

import com.example.pirosquare_spring.domain.Post;
import com.example.pirosquare_spring.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long create(Post post){
        postRepository.save(post);

        return post.getId();
    }

    public List<Post> findPosts(){
        return postRepository.findAll();
    }

    public Post findOne(Long postId){
        return postRepository.find(postId);
    }

    @Transactional
    public void update(Long id, String title, String content){
        Post post = postRepository.find(id);
        post.setTitle(title);
        post.setContent(content);
    }
}
