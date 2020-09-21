package com.garwalle.td2.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Groupe {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String nom;
	private String email;
	private String aliases;

	@ManyToOne
	private Organization organization;

	@ManyToMany
	@JoinTable(name = "user_groupe")
	private List<User> users;
}
