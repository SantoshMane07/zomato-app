package com.santoshmane.zomato_app.repositories;

import com.santoshmane.zomato_app.entities.Menu;
import com.santoshmane.zomato_app.entities.MenuItem;
import com.santoshmane.zomato_app.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {

    List<Menu> findByRestaurant(Restaurant restaurant);
}
