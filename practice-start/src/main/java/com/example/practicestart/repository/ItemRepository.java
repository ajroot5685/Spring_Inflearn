package com.example.practicestart.repository;

import com.example.practicestart.domain.Item;
import com.example.practicestart.dto.ItemSearchCond;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    Item save(Item item);

    Optional<Item> findById(Long id);

    List<Item> findAll(ItemSearchCond cond);

    Item update(Item item);

    void delete(long itemId);
}
