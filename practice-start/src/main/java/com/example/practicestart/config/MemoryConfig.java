package com.example.practicestart.config;

import com.example.practicestart.repository.MemoryItemRepository;
import com.example.practicestart.service.ItemService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemoryConfig {

    @Bean
    public ItemService itemService(){
        return new ItemService(memoryItemRepository());
    }

    @Bean
    public MemoryItemRepository memoryItemRepository(){
        return new MemoryItemRepository();
    }
}
