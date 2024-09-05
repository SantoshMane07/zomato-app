package com.santoshmane.zomato_app.strategies;

import com.santoshmane.zomato_app.entities.CustomerOrder;
import com.santoshmane.zomato_app.entities.OrderRequest;
import com.santoshmane.zomato_app.strategies.impl.DeliveryChargesDefaultCalculationStrategy;
import com.santoshmane.zomato_app.strategies.impl.DeliveryChargesSurgeCalculationStrategy;
import com.santoshmane.zomato_app.strategies.impl.DeliveryPartnerMatchingHighestRatedDeliveryPartnerStrategy;
import com.santoshmane.zomato_app.strategies.impl.DeliveryPartnerMatchingNearestDeliveryPartnerStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class CustomerOrderStrategyManager {
    private final DeliveryPartnerMatchingHighestRatedDeliveryPartnerStrategy highestRatedDeliveryPartnerStrategy;
    private final DeliveryPartnerMatchingNearestDeliveryPartnerStrategy nearestDeliveryPartnerStrategy;
    private final DeliveryChargesDefaultCalculationStrategy deliveryChargesDefaultCalculationStrategy;
    private final DeliveryChargesSurgeCalculationStrategy deliveryChargesSurgeCalculationStrategy;

    public DeliveryPartnerMatchingStrategy deliveryPartnerMatchingStrategy(Double customerRating){
        if(customerRating >= 4.8) {
            return highestRatedDeliveryPartnerStrategy;
        } else {
            return nearestDeliveryPartnerStrategy;
        }
    }

    public DeliveryChargesCalculationStrategy deliveryChargesCalculationStrategy(){
        LocalTime surgeStartTime = LocalTime.of(18,0);
        LocalTime surgeEndTime = LocalTime.of(23,0);
        LocalTime currentTime = LocalTime.now();

        boolean isSurgeTime = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);
        if (isSurgeTime){
            return deliveryChargesSurgeCalculationStrategy;
        }else{
            return deliveryChargesDefaultCalculationStrategy;
        }
    }
}
