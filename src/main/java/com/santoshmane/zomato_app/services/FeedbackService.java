package com.santoshmane.zomato_app.services;

import com.santoshmane.zomato_app.dtos.CustomerDto;
import com.santoshmane.zomato_app.dtos.DeliveryPartnerDto;
import com.santoshmane.zomato_app.dtos.FeedbackDto;
import com.santoshmane.zomato_app.dtos.RestaurantDto;
import com.santoshmane.zomato_app.entities.*;

public interface FeedbackService {

    void createNewFeedback(ConfirmedDelivery confirmedDelivery);
    DeliveryPartnerDto giveFeedbackToDeliveryPartner(Feedback feedback, Integer rating, String review);
    RestaurantDto giveFeedbackToRestaurant(Feedback feedback, Integer rating, String review);
    CustomerDto giveFeedbackToCustomer(Feedback feedback, Integer rating, String review);
    Feedback getFeedbackByCustomerOrder(CustomerOrder customerOrder);
}
