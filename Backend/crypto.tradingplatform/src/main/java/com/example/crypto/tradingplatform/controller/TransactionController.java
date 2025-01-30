package com.example.crypto.tradingplatform.controller;

import com.example.crypto.tradingplatform.entity.TransactionEntity;
import com.example.crypto.tradingplatform.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/api/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<TransactionEntity> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionEntity> getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TransactionEntity createTransaction(@RequestBody TransactionEntity transaction) {
        return transactionService.createTransaction(transaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionEntity> updateTransaction(
            @PathVariable Long id, @RequestBody TransactionEntity transaction) {
        return transactionService.updateTransaction(id, transaction)
                .map(ResponseEntity::ok) // If transaction is updated, return HTTP 200 with the updated entity
                .orElseGet(() -> ResponseEntity.notFound().build()); // If not found, return HTTP 404
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransactionById(@PathVariable Long id) {
        Optional<TransactionEntity> account = transactionService.getTransactionById(id);
        if (account.isPresent()) { // delete only if it exists
            transactionService.deleteTransaction(id);
            return ResponseEntity.ok().body("Deleted transaction successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllTransactions() {
        transactionService.deleteAllTransactions();
        return ResponseEntity.noContent().build();
    }
}