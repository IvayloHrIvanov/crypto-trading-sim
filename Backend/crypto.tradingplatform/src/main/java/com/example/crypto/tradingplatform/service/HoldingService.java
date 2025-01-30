package com.example.crypto.tradingplatform.service;

import com.example.crypto.tradingplatform.repository.HoldingRepo;
import com.example.crypto.tradingplatform.entity.HoldingEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HoldingService {

    private final HoldingRepo holdingRepo;

    public HoldingService(HoldingRepo holdingRepo) {
        this.holdingRepo = holdingRepo;
    }

    public List<HoldingEntity> getAllHoldings() {
        return holdingRepo.findAll();
    }

    public Optional<HoldingEntity> getHoldingById(Long id) {
        return holdingRepo.findById(id);
    }

    public Optional<HoldingEntity> getHoldingBySymbol(char[] symbol) {
        return holdingRepo.findBySymbol(symbol);
    }

    public HoldingEntity createHolding(HoldingEntity holding) {
        return holdingRepo.save(holding);
    }

    public Optional<HoldingEntity> updateHolding(Long id, HoldingEntity updatedHolding) {
        return holdingRepo.findById(id)
                .map(holding -> {
                    holding.setSymbol(updatedHolding.getSymbol());
                    holding.setQuantity(updatedHolding.getQuantity());
                    holding.setPrice(updatedHolding.getPrice());
                    return holdingRepo.save(holding);
                });
    }

    public void deleteHolding(Long id) {
        holdingRepo.deleteById(id);
    }

    public void deleteAllHoldings() {
        holdingRepo.deleteAll();
    }
}