package com.example.practicestart.config;

import com.example.practicestart.repository.ItemRepository;
import com.example.practicestart.repository.JpaItemRepository;
import com.example.practicestart.service.ItemService;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaConfig {

    private final EntityManager em;

    public JpaConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public ItemService itemService(){
        return new ItemService(itemRepository());
    }

    @Bean
    public ItemRepository itemRepository(){
        return new JpaItemRepository(em);
    }
}
