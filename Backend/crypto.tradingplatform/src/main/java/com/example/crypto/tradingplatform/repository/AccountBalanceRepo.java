package com.example.crypto.tradingplatform.repository;

import com.example.crypto.tradingplatform.entity.AccountBalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountBalanceRepo extends JpaRepository<AccountBalanceEntity, Long> {}