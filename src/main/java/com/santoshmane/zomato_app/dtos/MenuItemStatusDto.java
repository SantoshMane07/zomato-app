package com.santoshmane.zomato_app.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemStatusDto {
    @BooleanFlag
    @JsonProperty("isAvailable")
    private Boolean isAvailable;
}
