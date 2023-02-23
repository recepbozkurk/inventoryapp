package com.example.inventoryapp.dto;

import com.example.inventoryapp.entities.Store;
import org.springframework.stereotype.Component;

@Component
public class StoreDtoConverter {
    public StoreDto convert(Store store){
        return new StoreDto(store.getStoreId(), store.getName(), store.getAddress(), store.getDistrict(), store.getCity());
    }
}
