package com.santoshmane.zomato_app.services.impl;

import com.santoshmane.zomato_app.entities.Wallet;
import com.santoshmane.zomato_app.entities.WalletTransaction;
import com.santoshmane.zomato_app.repositories.WalletTransactionRepository;
import com.santoshmane.zomato_app.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {
    private final WalletTransactionRepository walletTransactionRepository;

    private final ModelMapper modelMapper;

    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {
        walletTransactionRepository.save(walletTransaction);
    }

    @Override
    public Page<WalletTransaction> getAllWalletTransactionsByWallet(Wallet wallet, PageRequest pageRequest) {
        return walletTransactionRepository.findByWallet(wallet,pageRequest);
    }
}
