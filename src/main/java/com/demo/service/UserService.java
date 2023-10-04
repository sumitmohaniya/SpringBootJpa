package com.demo.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Domain.User;
import com.demo.dto.UserDto;
import com.demo.repository.UserRepository;

@Service
public class UserService {

	
	@Autowired
	private UserRepository userRepository;

	public User addUser(UserDto userDto) {
		ModelMapper mapper = new ModelMapper();
		User user=mapper.map(userDto, User.class);
		userRepository.save(user);
		return user;
	}

	public User editUser(Long id, UserDto userDto) {
		Optional<User> isExist = userRepository.findById(id);
		if(isExist.isPresent()) {
			ModelMapper mapper = new ModelMapper();
			mapper.map(userDto, isExist.get());
			return userRepository.save(isExist.get());
		}
		return null;
		
	}

	public List<User> getAllUser() {

		return userRepository.findAll();
		
	}

	public boolean deleteUser(Long id) {
		Optional<User> isExist = userRepository.findById(id);
		if(isExist.isPresent()) {
			userRepository.delete(isExist.get());
			return true;
		}
		return false;
		
	}
	
	
}
