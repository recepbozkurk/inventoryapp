package com.example.inventoryapp.services;

import com.example.inventoryapp.entities.History;
import com.example.inventoryapp.entities.Product;
import com.example.inventoryapp.repositories.HistoryRepository;
import com.example.inventoryapp.utils.ResultEnums;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HistoryService {
    final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public Map getHistoryList(){
        Map<ResultEnums, Object> result = new LinkedHashMap<>();
        result.put(ResultEnums.status, true);
        result.put(ResultEnums.result, historyRepository.findAll().stream().collect(Collectors.toList()));
        result.put(ResultEnums.message, "");

        return result;
    }

    public Map saveHistory(History history){
        Map<ResultEnums, Object> result = new LinkedHashMap<>();
        result.put(ResultEnums.status, true);
        result.put(ResultEnums.result, historyRepository.save(history));
        result.put(ResultEnums.message, "");

        return result;
    }
}
