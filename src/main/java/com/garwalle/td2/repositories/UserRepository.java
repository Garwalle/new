package com.garwalle.td2.repositories;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.garwalle.td2.models.User;

public interface UserRepository extends Repository<User, Long> {
	List<User> findByFirstname(String firstname);
	
	List<User> findByLastname(String lastname);
}