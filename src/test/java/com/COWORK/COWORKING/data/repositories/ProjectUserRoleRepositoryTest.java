package com.COWORK.COWORKING.data.repositories;

import com.COWORK.COWORKING.data.models.ProjectUserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Sql(scripts ={"/database/data.sql"})
class ProjectUserRoleRepositoryTest {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ProjectUserRoleRepository projectUserRoleRepository;
    @Test
    void findRoleByUserAndProjectId() {
        ProjectUserRole projectUserRole = new ProjectUserRole();
        projectUserRole.setProject(projectRepository.findById(200L).orElse(null));
        projectUserRole.setUser(userRepository.findById("").orElse(null));
        projectUserRoleRepository.save(projectUserRole);
        assertThat(projectUserRoleRepository.findRoleByUserAndProjectId("",200L).get(0).getRole()).isNull();
        var PUR =projectUserRoleRepository.findRoleByUserAndProjectId("",200L);
        PUR.get(0).setRole(roleRepository.findRoleByRoleName("admin"));
        PUR=projectUserRoleRepository.saveAll(PUR);
        assertThat(PUR.get(0).getRole().getId()).isEqualTo(1L);
    }
}