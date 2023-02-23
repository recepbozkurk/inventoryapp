package com.example.inventoryapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private int productId;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false)
    private int amount;
    @Column(nullable = false)
    private int categoryId;
    private int storeId;
}
