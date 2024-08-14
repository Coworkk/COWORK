package com.COWORK.COWORKING.services.impl;

import com.COWORK.COWORKING.data.models.ProjectUserRole;
import com.COWORK.COWORKING.data.repositories.ProjectRepository;
import com.COWORK.COWORKING.data.repositories.ProjectUserRoleRepository;
import com.COWORK.COWORKING.data.repositories.RoleRepository;
import com.COWORK.COWORKING.data.repositories.UserRepository;
import com.COWORK.COWORKING.dtos.requests.AddMemberToProjectRequest;
import com.COWORK.COWORKING.dtos.responses.AddMemberToProjectResponse;
import com.COWORK.COWORKING.services.UserAndProjectServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserAndProjectServicesImpl implements UserAndProjectServices {
    private ProjectRepository projectRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ProjectUserRoleRepository projectUserRoleRepository;
    @Override
    public AddMemberToProjectResponse addMemberToProject(AddMemberToProjectRequest addMemberToProjectRequest) {
        if(!projectUserRoleRepository.findRoleByUserAndProjectId(addMemberToProjectRequest.getMemberId(),addMemberToProjectRequest.getProjectId()).isEmpty())throw new RuntimeException("something went wrong");
        ProjectUserRole projectUserRole = new ProjectUserRole();
        projectUserRole.setProject(projectRepository.findById(addMemberToProjectRequest.getProjectId()).orElseThrow(()-> new RuntimeException("project does not exist")));
        projectUserRole.setUser(userRepository.findById(addMemberToProjectRequest.getMemberId()).orElseThrow(()-> new RuntimeException("something went wrong")));
        projectUserRole=projectUserRoleRepository.save(projectUserRole);
        return AddMemberToProjectResponse.builder().projectUserRoleId(projectUserRole.getId())
        .memberId(projectUserRole.getUser().getUserId()).projectId(projectUserRole.getProject().getProjectId()).build();
    }
}
