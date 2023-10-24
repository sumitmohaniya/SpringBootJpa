package com.demo.Domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NonNull;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @NonNull
	private String name;
	private String grade;
	private String password;
	@OneToOne
	private User superviser;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIgnore
	private List<Role> role;
	
	@OneToMany
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIgnore
	private List<User> team;
	
	 User() {
		System.out.println("user constructor");
	}
}
