package com.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.Domain.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	Optional<User> findByName(String name);

	User findByNameAndPassword(String name, String password);

}
