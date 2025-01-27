package com.example.crypto.tradingplatform.controller;

import com.example.crypto.tradingplatform.service.KrakenWebSocketService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CryptoController {
    @Autowired
    private KrakenWebSocketService krakenWebSocketService;

    @CrossOrigin(origins = "http://localhost:5173/")
    @GetMapping("/api/crypto-prices")
    public Map<String, JsonNode> getCryptoPrices() {
        return krakenWebSocketService.getCryptoPrices();
    }
}