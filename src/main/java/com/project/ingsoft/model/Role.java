package com.project.ingsoft.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/*
 * Author: Alessio Spina
 * Class: Role
 * Description: An entity is annotated as @Entity, and has a special relationship with your database - generally 
 * 				each instance of an entity corresponds to a single row and the class itself corresponds to the table 
 * 				in which those rows are stored. In any case, we supply annotations for persistence on our entity classes. 
 * 				[Role is a Entity who rappresents a list of Roles situated in the table "Role" of DB. Rules could be: "admin",
 * 				"user" and "operator"]
 * 				
 * 				Un'entità è annotata come @Entity e ha una relazione speciale con il tuo database - in generale
 * 				ogni istanza di un'entità corrisponde a una singola riga e la classe stessa corrisponde alla tabella
 * 				in cui sono archiviate tali righe.
 * */


@Entity
@Table(name="role")
public class Role  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer role_id;
	
	@Column(name="role")
	private String role;
	
	
	public Integer getId() {
		return role_id;
	}
	public void setId(Integer id) {
		this.role_id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	

}
