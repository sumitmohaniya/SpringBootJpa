package com.demo.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.demo.util.ResponseHandler;


@RestController
@RequestMapping("api/v1")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@PostMapping("/role")
	public ResponseEntity<Object> addRole(@RequestBody RoleDto roleDto) {
		Role role=roleService.addRole(roleDto);
		if(Objects.nonNull(role)) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, "New role add successfully.......", role);
		}else {
			return ResponseHandler.generateResponse(HttpStatus.NOT_ACCEPTABLE, false, "some failue occur..........", role);
		}
		
	}
	
	@PutMapping("/role/{id}")
	public ResponseEntity<Object> editRole(@PathVariable("id") Long id ,@RequestBody RoleDto roleDto) {
		Role updateRole=roleService.editRole(id,roleDto);
		if(Objects.nonNull(updateRole)) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, "Role edit successfully......", updateRole);
		}else{
			return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT, false, "No role exist.......", updateRole);
		}
	}
	
	@GetMapping("/role")
	public ResponseEntity<Object> getAllRole() {
		List<Role> listOfRole=roleService.getAllRole();
		if(!listOfRole.isEmpty()) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, "Fetch successfully.......", listOfRole);
		}else{
			return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT, false, "No role exist.....", listOfRole);
		}
	}
	
	@DeleteMapping("/role/{id}")
	public ResponseEntity<Object> deleteRole(@PathVariable("id") Long id) {
		boolean isDeleted=roleService.deleteRole(id);
		if(isDeleted) {
			return ResponseHandler.generateResponse(HttpStatus.OK, isDeleted, "Deleted successfully.........", isDeleted);
		}else {
			return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT, isDeleted, "No user exist.......", isDeleted);
		}
	}
}
