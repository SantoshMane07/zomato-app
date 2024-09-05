package com.santoshmane.zomato_app.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.santoshmane.zomato_app.entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDto {

    private Long id;
    private Integer deliveryRating;
    private String deliveryReview;
    private Integer restaurantRating;
    private String restaurantReview;
    private String customerReview;
    private Integer customerRating;

    @JsonIgnore
    private CustomerOrder customerOrder;

    @JsonIgnore
    private Customer customer;

    @JsonIgnore
    private Restaurant restaurant;

    @JsonIgnore
    private DeliveryPartner deliveryPartner;

    @JsonIgnore
    private List<MenuItemFeedback> menuItemsFeedback;
}
