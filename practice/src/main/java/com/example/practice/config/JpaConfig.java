package com.example.practice.config;

import com.example.practice.repository.JpaItemRepository;
import com.example.practice.service.ItemService;
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
        return new ItemService(jpaItemRepository());
    }

    @Bean
    public JpaItemRepository jpaItemRepository(){
        return new JpaItemRepository(em);
    }
}
