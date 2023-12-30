package com.demo.controller;

import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Domain.User;
import com.demo.dto.UserDto;
import com.demo.jwt.JwtGeneratorInterface;
import com.demo.service.UserService;
import com.demo.util.ResponseHandler;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	  public ResponseEntity<?> registerUser(@RequestBody UserDto user){
	  try{
	     userService.addUser(user);
	     return new ResponseEntity<>(user, HttpStatus.CREATED);
	   } catch (Exception e){
	     return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	   }
	  }
	
	@PostMapping("/login")
	  public ResponseEntity<?> loginUser(@RequestParam(required=true)  String userName,@RequestParam(required=true) String password) {
	    try {
	    	System.out.println("login started....................");
	      Map<String,Object>  userDetails= userService.loginUser(userName,password);
	      if(userDetails.isEmpty()){
	    	  throw new Exception("UserName or Password is Invalid");
	      }
	       return new ResponseEntity<>(userDetails, HttpStatus.OK);
	    } catch (Exception e) {
	       return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	    }
	  }
	
	@PostMapping("/user")
	public ResponseEntity<Object> addUser(@RequestBody UserDto userDto) {
		User user=userService.addUser(userDto);
		if(Objects.nonNull(user))
			return ResponseHandler.generateResponse(HttpStatus.CREATED, true, "New user add successfully.......", user);
		else
			return ResponseHandler.generateResponse(HttpStatus.NOT_ACCEPTABLE, false, "Some failure Occur", user);
		
	}
	
	
	@PutMapping("user/{id}")
	public ResponseEntity<Object> editUser(@PathVariable("id") Long id ,@RequestBody UserDto userDto) {
		User updatedUser=userService.editUser(id,userDto);
		if(Objects.nonNull(updatedUser)) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, "User edit successfully.......", updatedUser);
		}else {
			return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT, false, "No user exist.......", updatedUser);
		}
	}
	
	@GetMapping("/user")
	public ResponseEntity<Object> getAllUser() {
		List<User> allUser=userService.getAllUser();
		if(!allUser.isEmpty()) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, "All user exist.......", allUser);
		}else {
			return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT, true, "No user exist.......", allUser);
		}
	}
	
	@GetMapping("/user/{name}")
	public ResponseEntity<Object> getUser(@PathVariable("name") String name) {
		User allUser=userService.getUserByName(name);
		if(Objects.nonNull(allUser)) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, "All user exist.......", allUser);
		}else {
			return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT, true, "No user exist.......", allUser);
		}
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable("id") Long id) {
		User allUser=userService.getUserById(id);
		if(Objects.nonNull(allUser)) {
			return ResponseHandler.generateResponse(HttpStatus.OK, true, "user exist.......", allUser);
		}else {
			return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT, true, "No user exist.......", allUser);
		}
	}
	
	@DeleteMapping("user/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id) {
		boolean isDeleted=userService.deleteUser(id);
		if(isDeleted) {
			return ResponseHandler.generateResponse(HttpStatus.OK, isDeleted, "Deleted Successfully.......", isDeleted);
		}else {
			return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT, isDeleted, "No user exist.......", isDeleted);
		}
	}
	
	@PostMapping("/github/webhooknotification")
	public ResponseEntity<Object> processWebhookNotification(@RequestBody Object payload){
		System.out.println("payload"+payload);
		userService.processWebhookNotification((Map<String,Object>)payload);
		return ResponseHandler.generateResponse(HttpStatus.OK, true, "details", null);


	}
}
