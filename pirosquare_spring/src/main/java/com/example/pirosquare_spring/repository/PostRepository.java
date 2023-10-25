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
//        return em.createQuery("select p from Post p", Post.class).getResultList();
        // user도 같이 출력
        return em.createQuery(
                "select p from Post p"+
                        " join fetch p.user u", Post.class)
                .getResultList();
    }

    public List<Post> titleSearchWithLike(String title){
        title = '%'+title+'%';
        return em.createQuery("select p from Post p where title like :title", Post.class)
                .setParameter("title", title)
                .getResultList();
    }

    public List<Post> orderingWithCreatetime(String filter){
        return em.createQuery("select p from Post p order by p.createtime desc").getResultList();
    }

    public void delete(Post post){
        em.remove(post);
    }
}
