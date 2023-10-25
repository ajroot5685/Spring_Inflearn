package com.example.pirosquare_spring.repository;

import com.example.pirosquare_spring.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User find(Long id){
        return em.find(User.class, id);
    }

    public List<User> findAll(){
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    public List<User> findAllWithPosts(int offset, int limit) {
        return em.createQuery(
                "select u from User u", User.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
