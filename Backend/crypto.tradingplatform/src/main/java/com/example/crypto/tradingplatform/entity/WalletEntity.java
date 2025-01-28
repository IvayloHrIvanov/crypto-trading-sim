package com.example.crypto.tradingplatform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wallet")
public class WalletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_id")
    private Long walletId;

    @Column(name = "holding_symbol", nullable = false)
    private char[] holdingSymbol;

    @Column(name = "holding_quantity", nullable = false)
    private int holdingQuantity;

    @Column(name = "holding_price", nullable = false)
    private double holdingPrice;
}