package com.example.practicestart.repository;

import com.example.practicestart.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    void save(Order order);

    Optional<Order> findById(Long id);

    List<Order> findAll();

    List<Order> findAllWithItems();
}
