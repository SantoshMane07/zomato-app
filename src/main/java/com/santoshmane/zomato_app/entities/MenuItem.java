package com.santoshmane.zomato_app.entities;

import com.santoshmane.zomato_app.entities.enums.MenuItemType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;
    private Boolean isAvailable;
    @Enumerated(EnumType.STRING)
    private MenuItemType menuItemType;
    private Double rating;
    private Double price;
}
