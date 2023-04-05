package com.ttu.blogapplication.repository;

import com.ttu.blogapplication.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    public Role findByRole(String role);

}
