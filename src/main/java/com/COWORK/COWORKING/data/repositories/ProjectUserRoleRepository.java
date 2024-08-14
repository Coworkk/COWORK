package com.COWORK.COWORKING.data.repositories;

import com.COWORK.COWORKING.data.models.ProjectUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectUserRoleRepository extends JpaRepository<ProjectUserRole, Long> {

    @Query(" select project_role from ProjectUserRole project_role where project_role.user.userId = :userId and project_role.project.projectId = :projectId")
    List<ProjectUserRole> findRoleByUserAndProjectId(Long userId, Long projectId);
}