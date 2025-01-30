package com.example.crypto.tradingplatform.repository;

import com.example.crypto.tradingplatform.entity.HoldingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface HoldingRepo extends JpaRepository<HoldingEntity, Long> {
    @Query("SELECT w FROM HoldingEntity w WHERE w.symbol = :symbol")
    Optional<HoldingEntity> findBySymbol(char[] symbol);
}