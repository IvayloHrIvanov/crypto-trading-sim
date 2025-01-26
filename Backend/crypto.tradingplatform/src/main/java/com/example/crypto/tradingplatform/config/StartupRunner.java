package com.example.crypto.tradingplatform.config;

import com.example.crypto.tradingplatform.service.KrakenWebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {
    @Autowired
    private KrakenWebSocketService krakenWebSocketService;

    @Override
    public void run(String[] args) {
        krakenWebSocketService.connect();
    }
}