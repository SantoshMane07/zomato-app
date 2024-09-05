package com.santoshmane.zomato_app.dtos;

import com.santoshmane.zomato_app.entities.MenuItem;
import com.santoshmane.zomato_app.entities.Feedback;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemFeedbackDto {

    private Long id;
    private Integer menuItemRating;
    private String menuItemReview;

    private MenuItem menuItem;

    private Feedback feedback;
}
