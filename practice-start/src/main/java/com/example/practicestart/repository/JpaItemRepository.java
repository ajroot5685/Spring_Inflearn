package com.example.practicestart.repository;

import com.example.practicestart.domain.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class JpaItemRepository implements ItemRepository {

    private final EntityManager em;

    public JpaItemRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Item save(Item item){
        em.persist(item);
        return item;
    }

    @Override
    public Optional<Item> findById(Long id){
        Item item = em.find(Item.class, id);
        return Optional.ofNullable(item);
    }

    @Override
    public List<Item> findAll(){
        String jpql = "select i from Item i";

        TypedQuery<Item> query = em.createQuery(jpql, Item.class);
        return query.getResultList();
    }

    @Override
    public Item update(Item item) {
        Item updateItem = em.find(Item.class, item.getId());

        updateItem.setItemName(item.getItemName());
        updateItem.setPrice(item.getPrice());
        updateItem.setQuantity(item.getQuantity());

        return updateItem;
    }

    @Override
    public void delete(long itemId) {
        Item deleteItem = em.find(Item.class, itemId);

        em.remove(deleteItem);
    }
}