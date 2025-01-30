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
@RequestMapping("/api/account-balance")
public class AccountBalanceController {

    private final AccountBalanceService accountBalanceService;

    public AccountBalanceController(AccountBalanceService accountBalanceService) {
        this.accountBalanceService = accountBalanceService;
    }

    /**
     * Get all account balances in the DB
     *
     * @return list of all account balances
     */
    @GetMapping
    public List<AccountBalanceEntity> getAllAccounts() {
        return accountBalanceService.getAllAccounts();
    }

    /**
     * Get account balance by Id
     *
     * @param id the Id of the account balance to retrieve
     * @return ResponseEntity with the account balance if found or 404 Not Found status
     */
    @GetMapping("/{id}")
    public ResponseEntity<AccountBalanceEntity> getAccountById(@PathVariable Long id) {
        return accountBalanceService.getAccountById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create new account balance
     *
     * @param accountBalance the account balance request body to create
     * @return ResponseEntity with the created account balance entity
     */
    @PostMapping
    public ResponseEntity<AccountBalanceEntity> createAccount(@RequestBody AccountBalanceEntity accountBalance) {
        AccountBalanceEntity createdAccount = accountBalanceService.createAccount(accountBalance);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    /**
     * Create new account balance with default data
     *
     * @return ResponseEntity with the created account balance
     */
    @PostMapping("/default")
    public ResponseEntity<AccountBalanceEntity> createAccountWithDefaultBalance() {
        AccountBalanceEntity createdAccount = accountBalanceService.createAccountWithDefaultBalance();
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    /**
     * Update an existing account balance by Id
     *
     * @param id the Id of the account balance to update
     * @param accountBalance the account balance request body to update
     * @return ResponseEntity with the updated account balance if found or 404 Not Found status
     */
    @PutMapping("/{id}")
    public ResponseEntity<AccountBalanceEntity> updateAccount(
            @PathVariable Long id, @RequestBody AccountBalanceEntity accountBalance) {
        return accountBalanceService.updateAccount(id, accountBalance)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Delete account balance by its Id
     *
     * @param id the Id of the account balance to delete
     * @return ResponseEntity with text to show if the deletion was successful or not
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable Long id) {
        Optional<AccountBalanceEntity> account = accountBalanceService.getAccountById(id);
        if (account.isPresent()) { // Delete only if it exists
            accountBalanceService.deleteAccount(id);
            return ResponseEntity.ok().body("Deleted account successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete all account balances in the DB
     *
     * @return ResponseEntity with text showing that the deletion was successful
     */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllAccounts() {
        accountBalanceService.deleteAllAccounts();
        return ResponseEntity.ok().body("Deleted all accounts successfully");
    }
}