package com.saqqa.library.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.management.relation.Role;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@ManyToOne(cascade = CascadeType.ALL)
	@Column(name = "role")
	private Role role;

	@Column(name = "dateOfCreation")
	private LocalDateTime dateOfCreation;
	@Column(name = "lastUpdated")
	private LocalDateTime lastUpdated;
}
