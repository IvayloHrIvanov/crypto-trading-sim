package com.example.crypto.tradingplatform.repository;

import com.example.crypto.tradingplatform.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WalletRepo extends JpaRepository<WalletEntity, Long> {
    @Query("SELECT w FROM WalletEntity w WHERE w.holdingSymbol = :symbol")
    Optional<WalletEntity> findBySymbol(char[] symbol);
}