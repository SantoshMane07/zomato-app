package com.santoshmane.zomato_app.services;

import com.santoshmane.zomato_app.entities.Wallet;
import com.santoshmane.zomato_app.entities.WalletTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface WalletTransactionService {
    void createNewWalletTransaction(WalletTransaction walletTransaction);
    Page<WalletTransaction> getAllWalletTransactionsByWallet(Wallet wallet, PageRequest pageRequest);
}
