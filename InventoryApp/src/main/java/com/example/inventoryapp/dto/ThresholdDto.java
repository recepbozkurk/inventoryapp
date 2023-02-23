package com.example.inventoryapp.dto;

import lombok.Data;

@Data
public class ThresholdDto {
    private Long id;
    private int productId;
    private int thresholdValue;

    public ThresholdDto(Long id, int productId, int thresholdValue) {
        this.id = id;
        this.productId = productId;
        this.thresholdValue = thresholdValue;
    }
}
