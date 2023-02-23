package com.example.inventoryapp.dto;

import lombok.Data;

@Data
public class CategoryDto {
    private Long categoryId;
    private String name;

    public CategoryDto(Long categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }
}
