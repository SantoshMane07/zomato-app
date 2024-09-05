package com.santoshmane.zomato_app.dtos;

import com.santoshmane.zomato_app.entities.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private Long contactNumber;
    private Set<Role> roles;
}
