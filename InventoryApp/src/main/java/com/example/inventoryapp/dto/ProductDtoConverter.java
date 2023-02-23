package com.example.inventoryapp.dto;

import com.example.inventoryapp.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoConverter {
    public ProductDto convert(Product product){
        return new ProductDto(
                product.getId(), product.getProductId(), product.getName(),
                product.getAmount(), product.getCategoryId(), product.getStoreId()
        );
    }
}
