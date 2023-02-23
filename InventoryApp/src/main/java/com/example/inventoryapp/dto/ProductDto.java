package com.example.inventoryapp.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private int productId;
    private String name;
    private int amount;
    private int categoryId;
    private int storeId;

    public ProductDto(Long id, int productId, String name, int amount, int categoryId, int storeId) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.amount = amount;
        this.categoryId = categoryId;
        this.storeId = storeId;
    }
}
