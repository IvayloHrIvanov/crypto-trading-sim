package com.example.crypto.tradingplatform.controller;

import com.example.crypto.tradingplatform.entity.TransactionEntity;
import com.example.crypto.tradingplatform.service.TransactionService;
import org.springframework.http.HttpStatus;
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

    /**
     * Get all transactions in the DB
     *
     * @return list of all transactions
     */
    @GetMapping
    public List<TransactionEntity> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    /**
     * Get a transaction by ID
     *
     * @param id the ID of the transaction to retrieve
     * @return ResponseEntity with the transaction if found or 404 Not Found status
     */
    @GetMapping("/{id}")
    public ResponseEntity<TransactionEntity> getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create new transaction
     *
     * @param transaction the transaction request body to create
     * @return ResponseEntity with the created transaction entity
     */
    @PostMapping
    public ResponseEntity<TransactionEntity> createTransaction(@RequestBody TransactionEntity transaction) {
        TransactionEntity createdTransaction = transactionService.createTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTransaction);
    }

    /**
     * Update an existing transaction by Id
     *
     * @param id the Id of the transaction to update
     * @param transaction the transaction request body to update
     * @return ResponseEntity with the updated transaction if found or 404 Not Found status
     */
    @PutMapping("/{id}")
    public ResponseEntity<TransactionEntity> updateTransaction(
            @PathVariable Long id, @RequestBody TransactionEntity transaction) {
        return transactionService.updateTransaction(id, transaction)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Delete transaction by Id
     *
     * @param id the Id of the transaction to delete
     * @return ResponseEntity with text to show if the deletion was successful or not
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransactionById(@PathVariable Long id) {
        Optional<TransactionEntity> account = transactionService.getTransactionById(id);
        if (account.isPresent()) { // Delete only if it exists
            transactionService.deleteTransaction(id);
            return ResponseEntity.ok().body("Deleted transaction successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete all transactions in the DB
     *
     * @return ResponseEntity with text showing that the deletion was successful
     */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllTransactions() {
        transactionService.deleteAllTransactions();
        return ResponseEntity.ok().body("Deleted all transactions successfully");
    }
}