package com.example.pirosquare_spring;

import com.example.pirosquare_spring.domain.Post;
import com.example.pirosquare_spring.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

//    @Test
//    @Transactional
////    @Rollback(false) // DB에 데이터가 제대로 들어갔는지 보고 싶을때
//    public void testPost() throws Exception{
//        //given
//        Post post = new Post();
//        post.setTitle("post1");
//
//        //when
//        Long saveId = postRepository.save(post);
//        Post findPost = postRepository.find(saveId);
//
//        //then
//        Assertions.assertThat(findPost.getId()).isEqualTo(post.getId());
//    }
}
