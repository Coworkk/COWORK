package com.COWORK.COWORKING.services;

import com.COWORK.COWORKING.data.models.Project;

public interface ProjectService {

    Project findProjectById(Long projectId);
}
