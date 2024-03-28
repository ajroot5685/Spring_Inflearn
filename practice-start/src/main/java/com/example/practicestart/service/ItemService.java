package com.example.practicestart.service;

import com.example.practicestart.domain.Item;
import com.example.practicestart.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Item save(Item item){
        return itemRepository.save(item);
    }

    public Optional<Item> findById(Long id){
        return itemRepository.findById(id);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }
}
