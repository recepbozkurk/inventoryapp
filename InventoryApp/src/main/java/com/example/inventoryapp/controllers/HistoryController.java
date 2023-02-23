package com.example.inventoryapp.controllers;

import com.example.inventoryapp.services.HistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/history")
public class HistoryController {
    final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/")
    public Map getHistoryList(){
        return historyService.getHistoryList();
    }
}
