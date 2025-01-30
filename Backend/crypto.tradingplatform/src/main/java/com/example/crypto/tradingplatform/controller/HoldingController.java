package com.example.crypto.tradingplatform.controller;

import com.example.crypto.tradingplatform.entity.HoldingEntity;
import com.example.crypto.tradingplatform.service.HoldingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/api/holding")
public class HoldingController {

    private final HoldingService holdingService;

    public HoldingController(HoldingService holdingService) {
        this.holdingService = holdingService;
    }

    @GetMapping
    public List<HoldingEntity> getAllHoldings() {
        return holdingService.getAllHoldings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HoldingEntity> getHoldingById(@PathVariable Long id) {
        return holdingService.getHoldingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/symbol/{symbol}")
    public ResponseEntity<HoldingEntity> getHoldingBySymbol(@PathVariable char[] symbol) {
        return holdingService.getHoldingBySymbol(symbol)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public HoldingEntity createHolding(@RequestBody HoldingEntity holding) {
        return holdingService.createHolding(holding);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HoldingEntity> updateHolding(
            @PathVariable Long id, @RequestBody HoldingEntity holding) {
        return holdingService.updateHolding(id, holding)
                .map(ResponseEntity::ok) // If holding is updated, return HTTP 200 with the updated entity
                .orElseGet(() -> ResponseEntity.notFound().build()); // If not found, return HTTP 404
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHolding(@PathVariable Long id) {
        Optional<HoldingEntity> account = holdingService.getHoldingById(id);
        if (account.isPresent()) { // delete only if it exists
            holdingService.deleteHolding(id);
            return ResponseEntity.ok().body("Deleted holding successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllHoldings() {
        holdingService.deleteAllHoldings();
        return ResponseEntity.noContent().build();
    }
}