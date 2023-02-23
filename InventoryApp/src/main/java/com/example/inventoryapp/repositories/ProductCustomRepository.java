package com.example.inventoryapp.repositories;

import com.example.inventoryapp.entities.Product;

import java.util.List;

public interface ProductCustomRepository {
    List<Product> findByFilter(Product filter);
}
