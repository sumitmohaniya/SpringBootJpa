package com.demo.jwt;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;


public interface JwtGeneratorInterface {
	Map<String, Object> generateToken(UserDetails user);
}
