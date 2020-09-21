package com.garwalle.td2.repositories;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.garwalle.td2.models.Groupe;

public interface GroupeRepository extends Repository<Groupe, Long> {
	List<Groupe> findByNom(String nom);

}