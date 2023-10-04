package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Domain.Role;
import com.demo.dto.RoleDto;
import com.demo.service.RoleService;


@RestController
@RequestMapping("api/v1/user/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@PostMapping("")
	public void addRole(@RequestBody RoleDto roleDto) {
		Role role=roleService.addRole(roleDto);
		
	}
	
	@PutMapping("/{id}")
	public void editRole(@PathVariable("id") Long id ,@RequestBody RoleDto roleDto) {
		roleService.editRole(id,roleDto);
	}
	
	@GetMapping("")
	public void getAllRole() {
		roleService.getAllRole();
	}
	
	@DeleteMapping("/{id}")
	public void deleteRole(@PathVariable("id") Long id) {
		roleService.deleteRole(id);
	}
}
