package com.example.practicestart.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item(String name, int price, int quantity) {
        this.itemName = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void addStock(int quantity) {
        this.quantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.quantity - quantity;
        if (restStock < 0) {
            throw new RuntimeException("need more stock");
        }
        this.quantity = restStock;
    }
}
