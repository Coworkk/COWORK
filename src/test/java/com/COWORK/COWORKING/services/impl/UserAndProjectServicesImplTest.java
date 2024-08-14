package com.COWORK.COWORKING.services.impl;

import com.COWORK.COWORKING.dtos.requests.AddMemberToProjectRequest;
import com.COWORK.COWORKING.dtos.responses.AddMemberToProjectResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Sql(scripts ={"/database/data.sql"})
class UserAndProjectServicesImplTest {
    @Autowired
    private UserAndProjectServicesImpl userAndProjectServices;
    @Test
    @Sql(scripts ={"/database/data.sql"})
    void addMemberToProject() {
        AddMemberToProjectRequest projectRequest = new AddMemberToProjectRequest();
        projectRequest.setMemberId("f62f68e8-023f-4c67-9e87-7af2a111e5eb");
        projectRequest.setProjectId(200L);
        AddMemberToProjectResponse response = userAndProjectServices.addMemberToProject(projectRequest);
        assertThat(response.getProjectUserRoleId()).isEqualTo(1L);
        assertThat(response.getRoleId()).isNull();
    }
    @Test
    void addSameMemberAgainToProject_ThrowsException() {
        AddMemberToProjectRequest projectRequest = new AddMemberToProjectRequest();
        projectRequest.setMemberId("f62f68e8-023f-4c67-9e87-7af2a111e5eb");
        projectRequest.setProjectId(200L);
        userAndProjectServices.addMemberToProject(projectRequest);
        assertThrows(RuntimeException.class, () -> userAndProjectServices.addMemberToProject(projectRequest));
    }
}