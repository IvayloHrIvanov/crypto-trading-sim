package com.example.crypto.tradingplatform.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Column(name = "asset_symbol", nullable = false)
    private char[] assetSymbol;

    @Column(name = "asset_quantity", nullable = false)
    private int assetQuantity;

    @Column(name = "asset_price", nullable = false)
    private double assetPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType;

    @Column(name = "profit_loss")
    private Double profit; //Wrapper Class so it can be assigned null value

    @ManyToOne
    @JoinColumn(name = "holding_id")
    @JsonBackReference
    private HoldingEntity holding;

    public enum TransactionType {
        BUY, SELL;
    }
}