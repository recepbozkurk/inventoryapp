package com.example.inventoryapp.controllers;

import com.example.inventoryapp.entities.Category;
import com.example.inventoryapp.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {
    final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public Map getCategoryList(){
        return categoryService.getCategoryList();
    }

    @PostMapping("/save")
    public Map saveCategory(@RequestBody Category category){
        return categoryService.saveCategory(category);
    }
}
