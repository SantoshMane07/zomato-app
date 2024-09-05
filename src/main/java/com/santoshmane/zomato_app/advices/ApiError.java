package com.santoshmane.zomato_app.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
@Data
public class ApiError {
    private HttpStatus status;
    private String message;
    private List<String> subErrors;
}
