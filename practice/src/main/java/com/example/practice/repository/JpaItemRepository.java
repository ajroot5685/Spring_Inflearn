package com.example.practice.repository;

import com.example.practice.domain.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@Transactional
public class JpaItemRepository {

    private final EntityManager em;

    public JpaItemRepository(EntityManager em) {
        this.em = em;
    }

    public Item save(Item item){
        em.persist(item);
        return item;
    }

    public Optional<Item> findById(Long id){
        Item item = em.find(Item.class, id);
        return Optional.ofNullable(item);
    }

    public List<Item> findAll(){
        String jpql = "select i from Item i";

        TypedQuery<Item> query = em.createQuery(jpql, Item.class);
        return query.getResultList();
    }
}
