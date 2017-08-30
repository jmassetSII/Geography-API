package com.springjpa.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springjpa.auth.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
