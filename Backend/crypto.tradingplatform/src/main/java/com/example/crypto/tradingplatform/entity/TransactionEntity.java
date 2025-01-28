package com.example.crypto.tradingplatform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Enumerated;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "transaction_symbol", nullable = false)
    private char[] transactionSymbol;

    @Column(name = "transaction_quantity", nullable = false)
    private int transactionQuantity;

    @Column(name = "transaction_price", nullable = false)
    private double transactionPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType;

    public enum TransactionType {
        BUY, SELL;
    }
}