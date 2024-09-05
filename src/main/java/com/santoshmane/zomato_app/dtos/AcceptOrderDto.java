package com.santoshmane.zomato_app.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcceptOrderDto {
    @NotBlank(message = "Estimated preparation time cannot be blank ")
    private Integer estimatedPreparationTime;
}
