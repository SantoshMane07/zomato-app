package com.santoshmane.zomato_app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping("/")
    public ResponseEntity<String> healthCheckController(){
        return ResponseEntity.ok("OK");
    }
}
