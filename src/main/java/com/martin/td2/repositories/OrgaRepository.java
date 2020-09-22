package com.martin.td2.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martin.td2.models.Organization;

public interface OrgaRepository extends JpaRepository<Organization, Integer> {
	// Find
	List<Organization> findAll();
	List<Organization> findByDomain(String domain);
	Organization findById(int id);
	
	// Delete
	List<Organization> deleteById(int id);
	    
	    
	    public Optional<Organization> findByName(String name);
	    
}
