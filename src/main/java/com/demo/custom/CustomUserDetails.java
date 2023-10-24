package com.demo.custom;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.demo.Domain.User;


public class CustomUserDetails implements UserDetails{
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private static final long serialVersionUID = -1206212136852711740L;
	/*
	 * private String username; private String password; private String email;
	 */
	User user;
	CustomUserDetails(User user){
		this.user=user;
		System.out.println("customer user details.................");
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	/*
	 * public String getUsername() { return username; } public void
	 * setUsername(String username) { this.username = username; } public String
	 * getPassword() { return password; } public void setPassword(String password) {
	 * this.password = password; } public String getEmail() { return email; } public
	 * void setEmail(String email) { this.email = email; }
	 */

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user.getName();
	}



}
