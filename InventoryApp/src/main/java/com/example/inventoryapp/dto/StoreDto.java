package com.example.inventoryapp.dto;

import lombok.Data;

@Data
public class StoreDto {
    private Long storeId;
    private String name;
    private String address;
    private String district;
    private String city;

    public StoreDto(Long storeId, String name, String address, String district, String city) {
        this.storeId = storeId;
        this.name = name;
        this.address = address;
        this.district = district;
        this.city = city;
    }
}
