package com.example.inventoryapp.repositories;

import com.example.inventoryapp.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}
