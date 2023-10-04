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

import com.demo.Domain.User;
import com.demo.dto.UserDto;
import com.demo.service.UserService;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

	@Autowired
	UserService userService;
		
	
	@PostMapping("")
	public void addUser(@RequestBody UserDto userDto) {
		User user=userService.addUser(userDto);
		
	}
	
	@PutMapping("/{id}")
	public void editUser(@PathVariable("id") Long id ,@RequestBody UserDto userDto) {
		userService.editUser(id,userDto);
	}
	
	@GetMapping("")
	public void getAllUser() {
		userService.getAllUser();
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") Long id) {
		userService.deleteUser(id);
	}
}
