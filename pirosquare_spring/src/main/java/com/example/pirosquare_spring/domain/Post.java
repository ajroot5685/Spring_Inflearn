package com.example.pirosquare_spring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String content;
    private LocalDateTime createtime;
    private LocalDateTime updatetime;

    public void setUser(User user){
        this.user = user;
        user.getPosts().add(this);
    }

    public Post() {
    }

    public static Post createPost(User user, String title, String content) {
        Post post= new Post();
        post.setUser(user);
        post.setTitle(title);
        post.setContent(content);
        post.setCreatetime(LocalDateTime.now());
        post.setUpdatetime(LocalDateTime.now());

        return post;
    }
}
