package com.example.practicestart.controller;

import com.example.practicestart.domain.Item;
import com.example.practicestart.service.ItemService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitItem {

    private final ItemService itemService;

    @PostConstruct
    public void init() {
        itemService.save(new Item("item1", 1000, 10));
        itemService.save(new Item("item2", 2000, 5));
    }
}
