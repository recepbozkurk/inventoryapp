package com.example.inventoryapp.repositories;

import com.example.inventoryapp.entities.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Integer> {
}
