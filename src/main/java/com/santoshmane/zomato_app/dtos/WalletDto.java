package com.santoshmane.zomato_app.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.santoshmane.zomato_app.entities.User;
import com.santoshmane.zomato_app.entities.WalletTransaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletDto {

    private Long id;

    @JsonIgnore
    private UserDto user;

    private double balance = 0.0;

    @JsonIgnore
    private List<WalletTransactionDto> transactions;
}
