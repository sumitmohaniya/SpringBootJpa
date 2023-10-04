package com.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Domain.Role;
import com.demo.dto.RoleDto;
import com.demo.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public Role addRole(RoleDto roleDto) {
		ModelMapper mapper = new ModelMapper();
		Role role=mapper.map(roleDto, Role.class);
		return roleRepository.save(role);
	}
	
	public Role editRole(Long id,RoleDto roleDto) {
		Optional<Role> isExist = roleRepository.findById(id);
		if(isExist.isPresent()) {
			ModelMapper mapper = new ModelMapper();
			mapper.map(roleDto, isExist.get());
			return roleRepository.save(isExist.get());
		}
		return null;
	}
	
	public List<Role> getAllRole() {
		List<Role> roleList=roleRepository.findAll();
		return roleList;
	}
	
	
	
	public boolean deleteRole(Long id) {
		Optional<Role> isExist = roleRepository.findById(id);
		if(isExist.isPresent()) {
			roleRepository.delete(isExist.get());
			return true;
		}
		return false;
	}
}
