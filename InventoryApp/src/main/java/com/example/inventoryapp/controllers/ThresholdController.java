package com.example.inventoryapp.controllers;

import com.example.inventoryapp.entities.Threshold;
import com.example.inventoryapp.services.ThresholdService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/threshold")
public class ThresholdController {
    final ThresholdService thresholdService;

    public ThresholdController(ThresholdService thresholdService) {
        this.thresholdService = thresholdService;
    }

    @GetMapping("/list")
    public Map getThresholdList(){
        return thresholdService.getThresholdList();
    }

    @PostMapping("/save")
    public Map saveThreshold(@RequestBody Threshold threshold){
        return thresholdService.saveThreshold(threshold);
    }
}
