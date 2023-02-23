package com.example.inventoryapp.controllers;

import com.example.inventoryapp.entities.Product;
import com.example.inventoryapp.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductControllers {
    final ProductService productService;

    public ProductControllers(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public Map getProductList(){
        return productService.getProductList();
    }

    @PostMapping("/filter")
    public Map getProductListByFilter(@RequestBody Product filter){
        return productService.getProductListByFilter(filter);
    }

    @PostMapping("/delete")
    public Map deleteProduct(@RequestParam int id){
        return productService.deleteProduct(id);
    }

    @PostMapping("/save")
    public Map saveProduct(@RequestBody Product product){
        productService.checkThresholdAndCreate(product.getProductId());

        return productService.saveProduct(product);
    }

    @PostMapping("/increase")
    public Map increaseProduct(@RequestParam int id, int amount){
        return productService.increaseProduct(id, amount);
    }

    @PostMapping("/decrease")
    public Map decreaseProduct(@RequestParam int id, int amount){
        return productService.decreaseProduct(id, amount);
    }

    @PostMapping("/search")
    public Map searchProduct(@RequestParam String name){
        return productService.searchProduct(name);
    }
}
