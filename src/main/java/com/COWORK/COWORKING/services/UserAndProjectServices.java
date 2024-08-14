package com.COWORK.COWORKING.services;

import com.COWORK.COWORKING.dtos.requests.AddMemberToProjectRequest;
import com.COWORK.COWORKING.dtos.responses.AddMemberToProjectResponse;

public interface UserAndProjectServices {
    AddMemberToProjectResponse addMemberToProject(AddMemberToProjectRequest addMemberToProjectRequest);
}
