package com.example.crypto.tradingplatform.service;

import com.example.crypto.tradingplatform.repository.AccountBalanceRepo;
import com.example.crypto.tradingplatform.entity.AccountBalanceEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountBalanceService {

    private final AccountBalanceRepo accountBalanceRepo;

    public AccountBalanceService(AccountBalanceRepo accountBalanceRepo) {
        this.accountBalanceRepo = accountBalanceRepo;
    }

    public List<AccountBalanceEntity> getAllAccounts() {
        return accountBalanceRepo.findAll();
    }

    public Optional<AccountBalanceEntity> getAccountById(Long id) {
        return accountBalanceRepo.findById(id);
    }

    public AccountBalanceEntity createAccount(AccountBalanceEntity account) {
        return accountBalanceRepo.save(account);
    }

    public AccountBalanceEntity createAccountWithDefaultBalance() {
        AccountBalanceEntity newAccount = new AccountBalanceEntity();

        return accountBalanceRepo.save(newAccount); // new accountBalance with default values managed by the DB
    }

    public Optional<AccountBalanceEntity> updateAccount(Long id, AccountBalanceEntity updatedAccountBalance) {
        return accountBalanceRepo.findById(id)
                .map(account -> {
                    account.setAccountBalance(updatedAccountBalance.getAccountBalance());
                    return accountBalanceRepo.save(account);
                });
    }

    public void deleteAccount(Long id) {
        accountBalanceRepo.deleteById(id);
    }

    public void deleteAllAccounts() {
        accountBalanceRepo.deleteAll();
    }
}
