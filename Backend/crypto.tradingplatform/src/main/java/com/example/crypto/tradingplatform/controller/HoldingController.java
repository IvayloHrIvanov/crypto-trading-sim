package com.example.crypto.tradingplatform.controller;

import com.example.crypto.tradingplatform.entity.HoldingEntity;
import com.example.crypto.tradingplatform.service.HoldingService;
import org.springframework.http.HttpStatus;
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

    /**
     * Get all holdings in the DB
     *
     * @return list of all holdings
     */
    @GetMapping
    public List<HoldingEntity> getAllHoldings() {
        return holdingService.getAllHoldings();
    }

    /**
     * Get holding by Id
     *
     * @param id the Id of the holding to retrieve
     * @return ResponseEntity with the holding if found or 404 Not Found status
     */
    @GetMapping("/{id}")
    public ResponseEntity<HoldingEntity> getHoldingById(@PathVariable Long id) {
        return holdingService.getHoldingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get holding by symbol
     *
     * @param symbol the symbol of the holding to retrieve
     * @return ResponseEntity with the holding if found or 404 Not Found status
     */
    @GetMapping("/symbol/{symbol}")
    public ResponseEntity<HoldingEntity> getHoldingBySymbol(@PathVariable char[] symbol) {
        return holdingService.getHoldingBySymbol(symbol)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create new holding
     *
     * @param holding the holding request body to create
     * @return ResponseEntity with the created holding entity
     */
    @PostMapping
    public ResponseEntity<HoldingEntity> createHolding(@RequestBody HoldingEntity holding) {
        HoldingEntity createdHolding = holdingService.createHolding(holding);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHolding);
    }

    /**
     * Update an existing holding by Id
     *
     * @param id the Id of the holding to update
     * @param holding the holding request body to update
     * @return ResponseEntity with the updated holding if found or 404 Not Found status
     */
    @PutMapping("/{id}")
    public ResponseEntity<HoldingEntity> updateHolding(
            @PathVariable Long id, @RequestBody HoldingEntity holding) {
        return holdingService.updateHolding(id, holding)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Delete holding by its Id
     *
     * @param id the Id of the holding to delete
     * @return ResponseEntity with text to show if the deletion was successful or not
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHolding(@PathVariable Long id) {
        Optional<HoldingEntity> account = holdingService.getHoldingById(id);
        if (account.isPresent()) { // Delete only if it exists
            holdingService.deleteHolding(id);
            return ResponseEntity.ok().body("Deleted holding successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete all holdings in the DB
     *
     * @return ResponseEntity with text showing that the deletion was successful
     */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllHoldings() {
        holdingService.deleteAllHoldings();
        return ResponseEntity.noContent().build();
    }
}