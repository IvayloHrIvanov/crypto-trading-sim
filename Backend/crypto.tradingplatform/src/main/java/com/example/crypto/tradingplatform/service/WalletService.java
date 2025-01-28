package com.example.crypto.tradingplatform.service;

import com.example.crypto.tradingplatform.repository.WalletRepo;
import com.example.crypto.tradingplatform.entity.WalletEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    private final WalletRepo walletRepo;

    public WalletService(WalletRepo walletRepo) {
        this.walletRepo = walletRepo;
    }

    public List<WalletEntity> getAllHoldings() {
        return walletRepo.findAll();
    }

    public Optional<WalletEntity> getHoldingById(Long id) {
        return walletRepo.findById(id);
    }

    public Optional<WalletEntity> getHoldingBySymbol(char[] symbol) {
        return walletRepo.findBySymbol(symbol);
    }

    public WalletEntity createHolding(WalletEntity holding) {
        return walletRepo.save(holding);
    }

    public Optional<WalletEntity> updateHolding(Long id, WalletEntity updatedHolding) {
        return walletRepo.findById(id)
                .map(holding -> {
                    holding.setHoldingSymbol(updatedHolding.getHoldingSymbol());
                    holding.setHoldingPrice(updatedHolding.getHoldingQuantity());
                    holding.setHoldingPrice(updatedHolding.getHoldingPrice());
                    return walletRepo.save(holding);
                });
    }

    public void deleteHolding(Long id) {
        walletRepo.deleteById(id);
    }

    public void deleteAllHoldings() {
        walletRepo.deleteAll();
    }
}