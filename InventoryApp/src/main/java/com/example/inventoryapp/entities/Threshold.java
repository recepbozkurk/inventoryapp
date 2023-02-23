package com.example.inventoryapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Threshold {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private int productId;
    private int thresholdValue;
}
