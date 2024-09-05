package com.santoshmane.zomato_app.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer menuItemRating;
    private String menuItemReview;

    @ManyToOne(fetch = FetchType.LAZY)
    private MenuItem menuItem;

    @ManyToOne(fetch = FetchType.LAZY)
    private Feedback feedback; // Association with the Review entity
}

