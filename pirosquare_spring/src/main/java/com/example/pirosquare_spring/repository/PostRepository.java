package com.example.pirosquare_spring.repository;

import com.example.pirosquare_spring.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    public void save(Post post) {
        em.persist(post);
    }

    public Post find(Long id){
        return em.find(Post.class, id);
    }

    public List<Post> findAll(){
        return em.createQuery("select p from Post p", Post.class).getResultList();
    }

    public List<Post> search(String keyword){
        keyword = '%'+keyword+'%';
        return em.createQuery("select p from Post p where title like :keyword", Post.class)
                .setParameter("keyword", keyword)
                .getResultList();
    }

    public List<Post> filtering(String filter){
        List<Post> posts = null;
        if (filter.equals("latest")){
            posts = em.createQuery("select p from Post p order by p.createtime desc").getResultList();
        }else if (filter.equals("popular")){
            // 좋아요 없음
            posts = em.createQuery("select p from Post p").getResultList();
        }else{
            posts = em.createQuery("select p from Post p").getResultList();
        }
        return posts;
    }
}
