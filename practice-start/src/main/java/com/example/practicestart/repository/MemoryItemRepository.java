package com.example.practicestart.repository;

import com.example.practicestart.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryItemRepository {

    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Optional<Item> findById(Long id){
        return Optional.ofNullable(store.get(id));
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }
}
