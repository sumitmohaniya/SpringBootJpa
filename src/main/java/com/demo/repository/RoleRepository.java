package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.Domain.Role;



public interface RoleRepository extends JpaRepository<Role,Long>{

}
