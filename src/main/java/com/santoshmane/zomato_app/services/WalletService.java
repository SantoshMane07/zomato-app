package com.santoshmane.zomato_app.services;

import com.santoshmane.zomato_app.entities.CustomerOrder;
import com.santoshmane.zomato_app.entities.User;
import com.santoshmane.zomato_app.entities.Wallet;
import com.santoshmane.zomato_app.entities.enums.TransactionMethod;

public interface WalletService {
    Wallet addMoneyToWallet(User user, Double amount, String transactionId, CustomerOrder customerOrder, TransactionMethod transactionMethod);
    Wallet deductMoneyFromWallet(User user,Double amount,String transactionId,CustomerOrder customerOrder,TransactionMethod transactionMethod);
    void withDrawAllMyMoneyFromWallet();
    Wallet findWalletById(Long walletId);
    Wallet createNewWallet(User user);
    Wallet findByUser(User user);
}
