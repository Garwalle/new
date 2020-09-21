package com.garwalle.td2.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private boolean suspended;

	@ManyToOne
	private Organization organization;

	@ManyToMany(mappedBy = "users")
	private List<Groupe> groupes;
}
