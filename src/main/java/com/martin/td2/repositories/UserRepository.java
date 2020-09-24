package com.martin.td2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.martin.td2.models.Organization;
import com.martin.td2.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findAll();
	User findById(int id);
	List<User> findByOrganization(Organization organization);
	
	@Modifying
	@Query("UPDATE User u set u.organization = null WHERE u.organization = :organization")
	void updateUserSetOrganization(@Param("organization") Organization organization);
}
