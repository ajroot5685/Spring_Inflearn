package com.example.practicestart.repository;

import com.example.practicestart.domain.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaOrderRepository implements OrderRepository {

    private final EntityManager em;


    @Override
    public void save(Order order) {
        em.persist(order);
    }

    @Override
    public Optional<Order> findById(Long id) {
        Order findOrder = em.find(Order.class, id);
        return Optional.ofNullable(findOrder);
    }

    @Override
    public List<Order> findAll() {
        String jpql = "select o from Order o";

        TypedQuery<Order> query = em.createQuery(jpql, Order.class);
        return query.getResultList();
    }

    @Override
    public List<Order> findAllWithItems() {
        String jpql = "select o from Order o" +
                " join fetch o.orderItems oi"+
                " join fetch oi.item i";

        return em.createQuery(jpql, Order.class).getResultList();
    }
}
