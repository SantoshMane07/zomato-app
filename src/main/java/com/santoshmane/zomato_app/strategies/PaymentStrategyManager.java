package com.santoshmane.zomato_app.strategies;

import com.santoshmane.zomato_app.entities.enums.PaymentMethod;
import com.santoshmane.zomato_app.strategies.impl.CashPaymentStrategy;
import com.santoshmane.zomato_app.strategies.impl.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {
    private final CashPaymentStrategy cashPaymentStrategy;
    private final WalletPaymentStrategy walletPaymentStrategy;

    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod){
        return switch (paymentMethod){
            case WALLET -> walletPaymentStrategy;
            case CASH -> cashPaymentStrategy;
        };
    }
}
