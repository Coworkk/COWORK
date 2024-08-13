package com.COWORK.COWORKING.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogInResponse {
    private String token;
    private String refreshToken;
}
