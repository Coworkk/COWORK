package com.COWORK.COWORKING.dtos.responses;

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
