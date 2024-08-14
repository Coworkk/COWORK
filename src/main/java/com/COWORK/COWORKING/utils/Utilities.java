package com.COWORK.COWORKING.utils;

import com.COWORK.COWORKING.data.models.RoleName;

import java.util.List;

import static com.COWORK.COWORKING.data.models.RoleName.*;
import static com.COWORK.COWORKING.data.models.RoleName.USER;

public class Utilities {
    public
    static final List<RoleName> allRoles =
         List.of(PROJECT_MANAGER,TEAM_MEMBER,
                PROJECT_SUPERVISOR,ADMIN,
                TEAM_LEAD,USER);

}
