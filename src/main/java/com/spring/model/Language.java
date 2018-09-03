package com.spring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="languages")
public class Language {
	
	@Id
	@Column(name="language_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="language_name")
	private String name;
	
	@ManyToMany(mappedBy="languages")
	private List<User> users = new ArrayList<User>();
	
	// __CONSTRUCTORS__ 

	public Language(String name) {
		this.name = name;
	}
	
	public Language() {
		
	}
	
	// Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
