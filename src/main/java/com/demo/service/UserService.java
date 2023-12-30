package com.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.demo.Domain.User;
import com.demo.custom.CustomUserDetails;
import com.demo.custom.CustomUserDetailsService;
import com.demo.dto.UserDto;
import com.demo.jwt.JwtGeneratorInterface;
import com.demo.mailService.MailService;
import com.demo.repository.RoleRepository;
import com.demo.repository.UserRepository;

@Service
public class UserService {

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	JwtGeneratorInterface jwtGeneratorInterface;
	
	 @Autowired 
	  private BCryptPasswordEncoder bCryptPasswordEncoder;
	 
	 @Autowired
		private CustomUserDetailsService customUserDetailsService;

	public User addUser(UserDto userDto) {
		ModelMapper mapper = new ModelMapper();
		User user=mapper.map(userDto, User.class);
		user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		userRepository.save(user);
		return user;
	}

	public User editUser(Long id, UserDto userDto) {
		Optional<User> isExist = userRepository.findById(id);
		if(isExist.isPresent()) {
			ModelMapper mapper = new ModelMapper();
			mapper.map(userDto, isExist.get());
			//mapper.getConfiguration().setAmbiguityIgnored(true);
			User user=isExist.get();
			 User superviser=userRepository.findById(userDto.getSuperviserIds()).get();
			 user.setSuperviser(superviser);
			 List<String> role=userDto.getRole();
			 user.setRole(roleRepository.findByRoleIn(role));
			return userRepository.save(user);
		}
		return null;
		
	}

	public List<User> getAllUser() {

		return userRepository.findAll();
		//return userList;
		
	}

	public boolean deleteUser(Long id) {
		Optional<User> isExist = userRepository.findById(id);
		if(isExist.isPresent()) {
			userRepository.delete(isExist.get());
			return true;
		}
		return false;
		
	}

	public User getUserByName(String name) {
		// TODO Auto-generated method stub
		Optional<User> optUser=userRepository.findByName(name);
		if(optUser.isPresent()) {
			User user=optUser.get();
			String toAddress="sumit.gupta@oodles.io";
			String headind="mail";
			Context context=new Context();
			context.setVariable("userName",user.getName());
			mailService.sendScheduleHtmlMail(toAddress,headind,context,"home",toAddress);
			return user;
		}
		return null;
	}

	public User getUserById(Long id) {
		/*
		 * WebClient client = WebClient.create("http://localhost:8086"); Mono<Object>
		 * employeeMono = client.get() .uri("/api/v1/userAccount/{id}", "1") .retrieve()
		 * .bodyToMono(Object.class); System.out.println("eeeeeeeee"+employeeMono);
		 * employeeMono.subscribe(System.out::println);
		 */
		return userRepository.findById(id).orElse(null);
		//return null;
	}
	

	public Map<String,Object> loginUser(String userName, String password) {
		//User user = userRepository.findByNameAndPassword(userName, password);
		UserDetails loadUserByUsername = customUserDetailsService.loadUserByUsername(userName);
		try {
			if(Objects.nonNull(loadUserByUsername)) {
				System.out.println("inside......"+password);
				if (bCryptPasswordEncoder.matches(password,loadUserByUsername.getPassword()) && loadUserByUsername != null) {
				//if(password.equals(loadUserByUsername.getPassword()) && loadUserByUsername!=null) {
					Map<String, Object> generateToken = jwtGeneratorInterface.generateToken(loadUserByUsername);
					return generateToken;
				}
			}
		}catch(Exception e) {
			System.out.println(e);
			new Exception("Invalid username or password!");
		}
		return null;
	}

	public void processWebhookNotification(Map<String, Object> payload) {
		// TODO Auto-generated method stub
		
	}
	
	
}
