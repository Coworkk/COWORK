package com.COWORK.COWORKING.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ApiResponse {
    private Object data;
    private boolean isSuccessful;
}
