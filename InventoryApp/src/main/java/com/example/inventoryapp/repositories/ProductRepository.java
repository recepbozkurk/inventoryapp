package com.example.inventoryapp.repositories;

import com.example.inventoryapp.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer>, ProductCustomRepository {
}
