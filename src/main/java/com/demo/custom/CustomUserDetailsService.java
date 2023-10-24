package com.demo.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.Domain.User;
import com.demo.repository.UserRepository;


@Service
public class CustomUserDetailsService  implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByName(username).orElseThrow(()-> 
		new UsernameNotFoundException("No user exist"));
		System.out.println("user"+user.getName());
		return new CustomUserDetails(user);
	}

	

}
