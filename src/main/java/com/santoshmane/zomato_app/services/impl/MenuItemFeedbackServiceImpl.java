package com.santoshmane.zomato_app.services.impl;

import com.santoshmane.zomato_app.dtos.MenuItemDto;
import com.santoshmane.zomato_app.dtos.MenuItemFeedbackDto;
import com.santoshmane.zomato_app.entities.MenuItem;
import com.santoshmane.zomato_app.entities.MenuItemFeedback;
import com.santoshmane.zomato_app.entities.Feedback;
import com.santoshmane.zomato_app.repositories.MenuItemFeedbackRepository;
import com.santoshmane.zomato_app.repositories.MenuItemRepository;
import com.santoshmane.zomato_app.services.MenuItemFeedbackService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemFeedbackServiceImpl implements MenuItemFeedbackService {
    private final MenuItemFeedbackRepository menuItemFeedbackRepository;
    private final ModelMapper modelMapper;
    private final MenuItemRepository menuItemRepository;

    @Override
    public List<MenuItemFeedback> getMenuItemsFeedbackByFeedback(Feedback feedback) {
        return menuItemFeedbackRepository.findByFeedback(feedback);
    }

    @Override
    public MenuItemDto giveFeedbackToCartItem(MenuItemFeedback menuItemFeedback, Integer rating, String review) {
        menuItemFeedback.setMenuItemReview(review);
        menuItemFeedback.setMenuItemRating(rating);
        MenuItemFeedback savedMenuItemFeedback = menuItemFeedbackRepository.save(menuItemFeedback);
        MenuItem menuItem = savedMenuItemFeedback.getMenuItem();
        //Update MenuItemRating
        Double newRating =menuItemFeedbackRepository.findByMenuItem(menuItem).stream()
                .mapToDouble(MenuItemFeedback::getMenuItemRating)
                .average().orElse(0.0);

        menuItem.setRating(newRating);
        MenuItem savedMenuItem = menuItemRepository.save(menuItem);
        return modelMapper.map(savedMenuItem, MenuItemDto.class);
    }
}
