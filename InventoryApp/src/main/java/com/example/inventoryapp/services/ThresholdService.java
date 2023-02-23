package com.example.inventoryapp.services;

import com.example.inventoryapp.dto.ThresholdDtoConverter;
import com.example.inventoryapp.entities.Threshold;
import com.example.inventoryapp.repositories.ThresholdRepository;
import com.example.inventoryapp.utils.ResultEnums;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ThresholdService {
    final ThresholdRepository thresholdRepository;
    final ThresholdDtoConverter thresholdDtoConverter;

    public ThresholdService(ThresholdRepository thresholdRepository, ThresholdDtoConverter thresholdDtoConverter) {
        this.thresholdRepository = thresholdRepository;
        this.thresholdDtoConverter = thresholdDtoConverter;
    }

    public Map getThresholdList(){
        Map<ResultEnums, Object> result = new LinkedHashMap<>();
        result.put(ResultEnums.status, true);
        result.put(ResultEnums.result, thresholdRepository.findAll().stream().map(thresholdDtoConverter::convert).collect(Collectors.toList()));
        result.put(ResultEnums.message, "");

        return result;
    }

    public Map saveThreshold(Threshold threshold){
        Map<ResultEnums, Object> result = new LinkedHashMap<>();
        result.put(ResultEnums.status, true);
        result.put(ResultEnums.result, thresholdDtoConverter.convert(thresholdRepository.save(threshold)));
        result.put(ResultEnums.message, "");

        return result;
    }
}
