package com.COWORK.COWORKING.dtos.requests;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogInRequest {
    private String username;
    private String password;
}

