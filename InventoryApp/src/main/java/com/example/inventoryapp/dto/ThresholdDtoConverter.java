package com.example.inventoryapp.dto;

import com.example.inventoryapp.entities.Threshold;
import org.springframework.stereotype.Component;

@Component
public class ThresholdDtoConverter {
    public ThresholdDto convert(Threshold threshold){
        return new ThresholdDto(threshold.getId(), threshold.getProductId(), threshold.getThresholdValue());
    }
}
