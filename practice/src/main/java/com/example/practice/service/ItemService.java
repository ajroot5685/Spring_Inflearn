package com.example.practice.service;

import com.example.practice.domain.Item;
import com.example.practice.repository.JpaItemRepository;
import com.example.practice.repository.MemoryItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

//    private final MemoryItemRepository memoryItemRepository;
    private final JpaItemRepository memoryItemRepository;

    public Item save(Item item){
        return memoryItemRepository.save(item);
    }

    public Optional<Item> findById(Long id){
        return memoryItemRepository.findById(id);
    }

    public List<Item> findItems(){
        return memoryItemRepository.findAll();
    }
}
