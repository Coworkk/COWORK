package com.COWORK.COWORKING.dtos.requests;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddMemberToProjectRequest {
    private String memberId;
    private Long projectId;
    private Long roleId;
}
