package com.santoshmane.zomato_app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompleteDeliveryDto {
    private String customerOrderOtp;
}
