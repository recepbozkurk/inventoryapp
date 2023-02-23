package com.example.inventoryapp.controllers;

import com.example.inventoryapp.entities.Store;
import com.example.inventoryapp.services.StoreService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/store")
public class StoreController {
    final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/list")
    public Map getStoreList(){
        return storeService.getStoreList();
    }

    @PostMapping("/save")
    public Map saveStore(@RequestBody Store store){
        return storeService.saveStore(store);
    }
}
