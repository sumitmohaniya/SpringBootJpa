package com.demo.Domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String grade;
	@OneToOne
	private User superviser;
	
	@ManyToMany
	private List<Role> role;
	
	@OneToMany
	private List<User> team;
	
	 User() {
		System.out.println("user constructor");
	}
}
