package com.example.ecomweb.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecomweb.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
}
