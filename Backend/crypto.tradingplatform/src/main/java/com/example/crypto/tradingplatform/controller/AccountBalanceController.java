package com.example.crypto.tradingplatform.controller;

import com.example.crypto.tradingplatform.entity.AccountBalanceEntity;
import com.example.crypto.tradingplatform.service.AccountBalanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/api/accountBalance")
public class AccountBalanceController {

    private final AccountBalanceService accountBalanceService;

    public AccountBalanceController(AccountBalanceService accountBalanceService) {
        this.accountBalanceService = accountBalanceService;
    }

    @GetMapping
    public List<AccountBalanceEntity> getAllAccounts() {
        return accountBalanceService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountBalanceEntity> getAccountById(@PathVariable Long id) {
        return accountBalanceService.getAccountById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AccountBalanceEntity> createAccount(@RequestBody AccountBalanceEntity accountBalance) {
        AccountBalanceEntity createdAccount = accountBalanceService.createAccount(accountBalance);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    @PostMapping("/default")
    public ResponseEntity<AccountBalanceEntity> createAccountWithDefaultBalance() {
        AccountBalanceEntity createdAccount = accountBalanceService.createAccountWithDefaultBalance();
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountBalanceEntity> updateAccount(
            @PathVariable Long id, @RequestBody AccountBalanceEntity accountBalance) {
        return accountBalanceService.updateAccount(id, accountBalance)
                .map(ResponseEntity::ok) // If account is updated, return HTTP 200 with the updated entity
                .orElseGet(() -> ResponseEntity.notFound().build()); // If not found, return HTTP 404
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable Long id) {
        Optional<AccountBalanceEntity> account = accountBalanceService.getAccountById(id);
        if (account.isPresent()) { // delete only if it exists
            accountBalanceService.deleteAccount(id);
            return ResponseEntity.ok().body("Deleted account successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllAccounts() {
        accountBalanceService.deleteAllAccounts();
        return ResponseEntity.ok().body("Deleted all accounts successfully");
    }
}