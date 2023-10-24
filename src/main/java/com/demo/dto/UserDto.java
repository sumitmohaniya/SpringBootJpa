package com.demo.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserDto {

	private String name;
	private String grade;
	private Long superviserIds;
	private String password;
	private List<String> role;

}
