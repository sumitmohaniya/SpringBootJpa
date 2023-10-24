package com.demo.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.Domain.Role;



public interface RoleRepository extends JpaRepository<Role,Long>{

	List<Role> findByRoleIn(List<String> role);

}
