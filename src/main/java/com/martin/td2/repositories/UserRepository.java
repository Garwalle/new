package com.martin.td2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martin.td2.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
}
