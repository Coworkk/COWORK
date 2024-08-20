package com.COWORK.COWORKING.services;

import com.COWORK.COWORKING.data.models.ProjectUserRole;
import com.COWORK.COWORKING.dtos.requests.AddMemberToProjectRequest;
import com.COWORK.COWORKING.dtos.requests.RoleResponse;
import com.COWORK.COWORKING.dtos.responses.AddMemberToProjectResponse;

import java.util.List;

public interface UserAndProjectServices {
    AddMemberToProjectResponse addMemberToProject(AddMemberToProjectRequest addMemberToProjectRequest);

    List<ProjectUserRole> findMemberInProject(Long projectId , String userId);

    RoleResponse assignRoleToMemberInProject(Long projectId , String userId , String roleName);
}
