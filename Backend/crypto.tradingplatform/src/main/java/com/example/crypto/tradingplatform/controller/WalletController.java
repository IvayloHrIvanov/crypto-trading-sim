package com.example.crypto.tradingplatform.controller;

import com.example.crypto.tradingplatform.entity.WalletEntity;
import com.example.crypto.tradingplatform.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping
    public List<WalletEntity> getAllHoldings() {
        return walletService.getAllHoldings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletEntity> getHoldingById(@PathVariable Long id) {
        return walletService.getHoldingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/symbol/{symbol}")
    public ResponseEntity<WalletEntity> getHoldingBySymbol(@PathVariable char[] symbol) {
        return walletService.getHoldingBySymbol(symbol)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public WalletEntity createHolding(@RequestBody WalletEntity holding) {
        return walletService.createHolding(holding);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WalletEntity> updateHolding(
            @PathVariable Long id, @RequestBody WalletEntity holding) {
        return walletService.updateHolding(id, holding)
                .map(ResponseEntity::ok) // If holding is updated, return HTTP 200 with the updated entity
                .orElseGet(() -> ResponseEntity.notFound().build()); // If not found, return HTTP 404
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHolding(@PathVariable Long id) {
        Optional<WalletEntity> account = walletService.getHoldingById(id);
        if (account.isPresent()) { // delete only if it exists
            walletService.deleteHolding(id);
            return ResponseEntity.ok().body("Deleted wallet successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllHoldings() {
        walletService.deleteAllHoldings();
        return ResponseEntity.noContent().build();
    }
}