package com.COWORK.COWORKING.services.impl;

import com.COWORK.COWORKING.data.models.ProjectUserRole;
import com.COWORK.COWORKING.data.models.Role;
import com.COWORK.COWORKING.data.models.RoleName;
import com.COWORK.COWORKING.data.repositories.ProjectRepository;
import com.COWORK.COWORKING.data.repositories.ProjectUserRoleRepository;
import com.COWORK.COWORKING.data.repositories.RoleRepository;
import com.COWORK.COWORKING.data.repositories.UserRepository;
import com.COWORK.COWORKING.dtos.requests.AddMemberToProjectRequest;
import com.COWORK.COWORKING.dtos.requests.RoleResponse;
import com.COWORK.COWORKING.dtos.responses.AddMemberToProjectResponse;
import com.COWORK.COWORKING.services.UserAndProjectServices;
import com.COWORK.COWORKING.utils.Utilities;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserAndProjectServicesImpl implements UserAndProjectServices {
    private ProjectRepository projectRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ProjectUserRoleRepository projectUserRoleRepository;
    private final UserServicesImpl userServices;
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
    @Override
    public List<ProjectUserRole> findMemberInProject(Long projectId, String userId) {
        return projectUserRoleRepository.findRoleByUserAndProjectId(userId, projectId);
    }

    @Override
    public RoleResponse assignRoleToMemberInProject(Long projectId, String userId, String roleName) {
            ProjectUserRole projectUserRole;
        List<ProjectUserRole> projectUserRoles =findMemberInProject(projectId, userId);
            if(projectUserRoles.isEmpty())throw new RuntimeException("something went wrong , member not in project");
            RoleName rolename = RoleName.valueOf(roleName.toUpperCase());
            if(!Utilities.allRoles.contains(rolename)) throw new RuntimeException("check available roles and try again");
            Role role = new Role();
            role.setRoleName(rolename);
            if(roleRepository.findRoleByRoleName(roleName)==null){
                role =roleRepository.save(role);
                userServices.addARoleToProject(roleName);}
           projectUserRole=projectUserRoles.get(0);
           projectUserRole.setRole(roleRepository.findRoleByRoleName(roleName));
           projectUserRole =projectUserRoleRepository.save(projectUserRole);
           return RoleResponse.builder().message("role successfully assigned").build();
    }


}
