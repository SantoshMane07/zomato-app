package com.santoshmane.zomato_app.services;

import com.santoshmane.zomato_app.dtos.MenuItemDto;
import com.santoshmane.zomato_app.dtos.MenuItemFeedbackDto;
import com.santoshmane.zomato_app.dtos.FeedbackDto;
import com.santoshmane.zomato_app.entities.MenuItemFeedback;
import com.santoshmane.zomato_app.entities.Feedback;

import java.util.List;

public interface MenuItemFeedbackService {

    List<MenuItemFeedback> getMenuItemsFeedbackByFeedback(Feedback feedback);

    MenuItemDto giveFeedbackToCartItem(MenuItemFeedback menuItemFeedback, Integer rating, String review);
}
