package com.example.inventoryapp.services;

import com.example.inventoryapp.dto.CategoryDtoConverter;
import com.example.inventoryapp.entities.Category;
import com.example.inventoryapp.repositories.CategoryRepository;
import com.example.inventoryapp.utils.ResultEnums;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    final CategoryRepository categoryRepository;
    final CategoryDtoConverter categoryDtoConverter;


    public CategoryService(CategoryRepository categoryRepository, CategoryDtoConverter categoryDtoConverter) {
        this.categoryRepository = categoryRepository;
        this.categoryDtoConverter = categoryDtoConverter;
    }

    public Map getCategoryList(){
        Map<ResultEnums, Object> result = new LinkedHashMap<>();
        result.put(ResultEnums.status, true);
        result.put(ResultEnums.result, categoryRepository.findAll().stream().map(categoryDtoConverter::convert).collect(Collectors.toList()));
        result.put(ResultEnums.message, "");

        return result;
    }

    public Map saveCategory(Category category){
        Map<ResultEnums, Object> result = new LinkedHashMap<>();
        result.put(ResultEnums.status, true);
        result.put(ResultEnums.result, categoryDtoConverter.convert(categoryRepository.save(category)));
        result.put(ResultEnums.message, "");

        return result;
    }
}
