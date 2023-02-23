package com.example.inventoryapp.dto;

import com.example.inventoryapp.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoConverter {
    public CategoryDto convert(Category category){
        return new CategoryDto(category.getCategoryId(), category.getName());
    }
}
