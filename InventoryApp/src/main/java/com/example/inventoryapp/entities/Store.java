package com.example.inventoryapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long storeId;
    private String name;
    @Column(length = 300)
    private String address;
    @Column(length = 50)
    private String district;
    @Column(length = 50)
    private String city;
}
