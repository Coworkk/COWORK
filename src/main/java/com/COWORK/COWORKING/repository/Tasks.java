package com.COWORK.COWORKING.repository;

import com.COWORK.COWORKING.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Tasks extends JpaRepository<Task,Long> {

}
