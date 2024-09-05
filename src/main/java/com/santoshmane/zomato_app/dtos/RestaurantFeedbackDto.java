package com.santoshmane.zomato_app.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantFeedbackDto {
    @NotNull(message = "Review cannot be null.")
    @Size(max = 500, message = "Review cannot exceed 500 characters.")
    private String review;
    @NotNull(message = "Rating cannot be null.")
    @Min(value = 1, message = "Rating must be at least 1.")
    @Max(value = 5, message = "Rating must be no more than 5.")
    private Integer rating;
}
