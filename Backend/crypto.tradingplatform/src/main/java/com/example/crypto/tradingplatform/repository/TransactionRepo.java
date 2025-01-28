package com.example.crypto.tradingplatform.repository;

import com.example.crypto.tradingplatform.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<TransactionEntity, Long> {}