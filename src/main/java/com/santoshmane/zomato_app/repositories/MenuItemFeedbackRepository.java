package com.santoshmane.zomato_app.repositories;

import com.santoshmane.zomato_app.entities.MenuItem;
import com.santoshmane.zomato_app.entities.MenuItemFeedback;
import com.santoshmane.zomato_app.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface MenuItemFeedbackRepository extends JpaRepository<MenuItemFeedback,Long> {
    List<MenuItemFeedback> findByFeedback(Feedback feedback);

    List<MenuItemFeedback> findByMenuItem(MenuItem menuItem);
}
