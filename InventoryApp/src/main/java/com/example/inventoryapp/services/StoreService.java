package com.example.inventoryapp.services;

import com.example.inventoryapp.dto.StoreDtoConverter;
import com.example.inventoryapp.entities.Store;
import com.example.inventoryapp.repositories.StoreRepository;
import com.example.inventoryapp.utils.ResultEnums;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StoreService {
    final StoreRepository storeRepository;
    final StoreDtoConverter storeDtoConverter;


    public StoreService(StoreRepository storeRepository, StoreDtoConverter storeDtoConverter) {
        this.storeRepository = storeRepository;
        this.storeDtoConverter = storeDtoConverter;
    }

    public Map getStoreList(){
        Map<ResultEnums, Object> result = new LinkedHashMap<>();
        result.put(ResultEnums.status, true);
        result.put(ResultEnums.result, storeRepository.findAll().stream().map(storeDtoConverter::convert).collect(Collectors.toList()));
        result.put(ResultEnums.message, "");

        return result;
    }

    public Map saveStore(Store store){
        Map<ResultEnums, Object> result = new LinkedHashMap<>();
        result.put(ResultEnums.status, true);
        result.put(ResultEnums.result, storeDtoConverter.convert(storeRepository.save(store)));
        result.put(ResultEnums.message, "");

        return result;
    }
}
