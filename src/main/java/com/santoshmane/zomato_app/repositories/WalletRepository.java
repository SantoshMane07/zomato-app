package com.santoshmane.zomato_app.repositories;

import com.santoshmane.zomato_app.entities.User;
import com.santoshmane.zomato_app.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet,Long> {
    Optional<Wallet> findByUser(User user);
}
