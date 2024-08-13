package com.COWORK.COWORKING.dtos.responses;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String username;
    private String id;
    private String firstName;
    private String lastName;
}
