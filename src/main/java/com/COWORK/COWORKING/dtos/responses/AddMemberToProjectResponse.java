package com.COWORK.COWORKING.dtos.responses;
import lombok.*;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddMemberToProjectResponse {
    private Long projectUserRoleId;
    private String memberId;
    private Long projectId;
    private Long roleId;
}
