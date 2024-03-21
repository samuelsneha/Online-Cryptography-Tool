package com.cgtmse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table ( name="FYP_USER")
public class User {
	
	
//	@Column( name="id")
//	@GeneratedValue( strategy = GenerationType.AUTO)
//	private int id;
	@Column( name="fname")
	private String firstName;
	@Column( name="lname")
	private String lastName;
	@Id
	@Column( name="email")
	private String email;
	@Column( name="password")
	private String password;
	
	public User() {
		super();
	}
	
	public User( String firstName, String lastName, String email, String password) {
		super();
	//	this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

//	public int getId() {
//		return id;
//	}

//	public void setId(int id) {
//		this.id = id;
//	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

