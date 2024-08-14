package com.COWORK.COWORKING.data.repositories;

import com.COWORK.COWORKING.data.models.Role;
import com.COWORK.COWORKING.utils.Utilities;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.COWORK.COWORKING.data.models.RoleName.USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RoleRepositoryTest {
    @Autowired
   private RoleRepository roleRepository;
    @Test
    void findRoleByRoleName() {
        Role role = new Role();
        role.setRoleName(USER);
        roleRepository.save(role);
        role =roleRepository.findRoleByRoleName("uSer");
        assertThat(role.getRoleName().name()).isEqualTo("USER");
        assertTrue(Utilities.allRoles.contains(role.getRoleName()));
        System.out.println(role.getRoleName());
    }
}