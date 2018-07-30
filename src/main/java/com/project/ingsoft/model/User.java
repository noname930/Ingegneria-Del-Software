package com.project.ingsoft.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/*
 * Author: Alessio Spina
 * Class: User
 * Description: An entity is annotated as @Entity, and has a special relationship with your database - generally 
 * 				each instance of an entity corresponds to a single row and the class itself corresponds to the table 
 * 				in which those rows are stored. In any case, we supply annotations for persistence on our entity classes. 
 * 				[User is a Entity who rappresents a list of accounts situated in the table "User" of DB.]
 * 				
 * 				Un'entità è annotata come @Entity e ha una relazione speciale con il tuo database - in generale
 * 				ogni istanza di un'entità corrisponde a una singola riga e la classe stessa corrisponde alla tabella
 * 				in cui sono archiviate tali righe.
 * */

@Entity
@Table(name="user")
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	@Column(name="enabled")
	private Integer enabled;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	
	

}
