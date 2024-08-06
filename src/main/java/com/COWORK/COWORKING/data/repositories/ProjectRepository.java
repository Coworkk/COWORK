package com.COWORK.COWORKING.data.repositories;

import com.COWORK.COWORKING.data.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
