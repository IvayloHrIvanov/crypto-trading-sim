package com.example.crypto.tradingplatform.service;

import com.example.crypto.tradingplatform.entity.TransactionEntity;
import com.example.crypto.tradingplatform.repository.TransactionRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepo transactionRepo;

    public TransactionService(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    public List<TransactionEntity> getAllTransactions() {
        return transactionRepo.findAll();
    }

    public Optional<TransactionEntity> getTransactionById(Long id) {
        return transactionRepo.findById(id);
    }

    public TransactionEntity createTransaction(TransactionEntity transaction) {
        return transactionRepo.save(transaction);
    }

    public Optional<TransactionEntity> updateTransaction(Long id, TransactionEntity updatedTransaction) {
        return transactionRepo.findById(id)
                .map(transaction -> {
                    transaction.setTransactionSymbol(updatedTransaction.getTransactionSymbol());
                    transaction.setTransactionQuantity(updatedTransaction.getTransactionQuantity());
                    transaction.setTransactionPrice(updatedTransaction.getTransactionPrice());
                    transaction.setTransactionType(updatedTransaction.getTransactionType());
                    return transactionRepo.save(transaction);
                });
    }

    public void deleteTransaction(Long id) {
        transactionRepo.deleteById(id);
    }

    public void deleteAllTransactions() {
        transactionRepo.deleteAll();
    }
}