package com.example.demo.post.service;

import com.example.demo.mock.FakePostRepository;
import com.example.demo.mock.FakeUserRepository;
import com.example.demo.mock.TestClockHolder;
import com.example.demo.post.domain.Post;
import com.example.demo.post.domain.PostCreate;
import com.example.demo.post.domain.PostUpdate;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PostServiceTest {

    private PostServiceImpl postService;

    @BeforeEach
    void init() {
        FakeUserRepository fakeUserRepository = new FakeUserRepository();
        FakePostRepository fakePostRepository = new FakePostRepository();
        this.postService = PostServiceImpl.builder()
                .postRepository(fakePostRepository)
                .userRepository(fakeUserRepository)
                .clockHolder(new TestClockHolder(1678530673958L))
                .build();

        User savedUser = fakeUserRepository.save(User.builder()
                .id(1L)
                .email("kok202@naver.com")
                .address("Seoul")
                .nickname("kok202")
                .certificationCode("aaaaaaaa-aaaaaaa-aaaa-aaaaaaaaa-aaaaaa")
                .status(UserStatus.ACTIVE)
                .lastLoginAt(0L)
                .build());
        fakeUserRepository.save(User.builder()
                .id(2L)
                .email("kok203@naver.com")
                .address("Seoul")
                .nickname("kok203")
                .certificationCode("aaaaaaaa-aaaaaaa-aaaa-aaaaaaaaa-aaaaab")
                .status(UserStatus.PENDING)
                .lastLoginAt(0L)
                .build());
        fakePostRepository.save(Post.builder()
                .id(1L)
                .content("helloworld")
                .createdAt(1678530673958L)
                .modifiedAt(1678530673958L)
                .writer(savedUser)
                .build());
    }

    @Test
    void getById는_존재하는_게시물을_내려준다() {
        // given
        // when
        Post result = postService.getById(1);

        // then
        assertThat(result.getContent()).isEqualTo("helloworld");
        assertThat(result.getWriter().getEmail()).isEqualTo("kok202@naver.com");
    }

    @Test
    void postCreateDto_를_이용하여_게시물을_생성할_수_있다() {
        // given
        PostCreate postCreateDto = PostCreate.builder()
                .writerId(1)
                .content("foobar")
                .build();

        // when
        Post result = postService.create(postCreateDto);

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getContent()).isEqualTo("foobar");
        assertThat(result.getCreatedAt()).isEqualTo(1678530673958L);
        assertThat(result.getModifiedAt()).isEqualTo(1678530673958L);
    }

    @Test
    void PostUpdateDto_를_이용하여_게시물을_수정할_수_있다() {
        // given
        PostUpdate postUpdateDto = PostUpdate.builder()
                .content("hello world")
                .build();

        // when
        postService.update(1, postUpdateDto);

        // then
        Post post = postService.getById(1);
        assertThat(post.getContent()).isEqualTo("hello world");
        assertThat(post.getCreatedAt()).isEqualTo(1678530673958L);
        assertThat(post.getModifiedAt()).isEqualTo(1678530673958L);
    }
}