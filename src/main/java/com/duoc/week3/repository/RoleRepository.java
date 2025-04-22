package com.duoc.week3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duoc.week3.model.Role;

// Repository layer for User
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
