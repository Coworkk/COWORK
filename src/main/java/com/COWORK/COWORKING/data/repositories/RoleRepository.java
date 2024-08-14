package com.COWORK.COWORKING.data.repositories;

import com.COWORK.COWORKING.data.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

public interface RoleRepository extends JpaRepository<Role, Long> {
   Role findRoleById(Long id);
   @Query("select role from Role role where upper(role.roleName) =  upper(:roleName)")
   Role findRoleByRoleName(String roleName);

//   @Query("select r from Role r where r.role) like :unknownAttr1)")
//   @NonNull
//   Role getRoleByRoleNameIgnoreCase(@Param("unknownAttr1") @NonNull Object unknownAttr1);
}