package com.example.inventoryapp.repositories;

import com.example.inventoryapp.entities.Threshold;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThresholdRepository extends JpaRepository<Threshold, Integer> {
}
