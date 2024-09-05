package com.santoshmane.zomato_app.repositories;

import com.santoshmane.zomato_app.entities.Wallet;
import com.santoshmane.zomato_app.entities.WalletTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction,Long> {
    Page<WalletTransaction> findByWallet(Wallet wallet, PageRequest pageRequest);
}
