package com.example.pirosquare_spring.service;

import com.example.pirosquare_spring.domain.Post;
import com.example.pirosquare_spring.domain.User;
import com.example.pirosquare_spring.repository.PostRepository;
import com.example.pirosquare_spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long create(Long userId, String title, String content){
        User user = userRepository.find(userId);

        Post post = Post.createPost(user, title, content);
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
    public Long update(Long id, String title, String content){
        Post post = postRepository.find(id);
        post.setTitle(title);
        post.setContent(content);

        return post.getId();
    }

    public List<Post> search(String keyword){
        return postRepository.titleSearchWithLike(keyword);
    }

    public List<Post> filtering(String filter){
        if (filter.equals("latest")){
            return postRepository.orderingWithCreatetime(filter);
        }else if(filter.equals("popular")){
            // 좋아요 구현 안되어있음
            return postRepository.findAll();
        }else{
            return postRepository.findAll();
        }
    }

    @Transactional
    public Long delete(Long id){
        Post post = postRepository.find(id);
        if (post==null){
            return (long)-1;
        }

        Long deletedId = post.getId();

        postRepository.delete(post);

        return deletedId;
    }
}
