package com.example.practicestart.config;

import com.example.practicestart.repository.ItemRepository;
import com.example.practicestart.repository.MemoryItemRepository;
import com.example.practicestart.service.ItemService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class MemoryConfig {

    @Bean
    public ItemService itemService(){
        return new ItemService(itemRepository());
    }

    @Bean
    public ItemRepository itemRepository(){
        return new MemoryItemRepository();
    }
}
