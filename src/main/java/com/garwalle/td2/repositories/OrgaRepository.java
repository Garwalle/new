package com.garwalle.td2.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.garwalle.td2.models.Organization;

public interface OrgaRepository extends CrudRepository<Organization, Long> {
	List<Organization> findByNom(String nom);

	List<Organization> findByDomain(String domain);
	
	List<Organization> findById(int id);
}