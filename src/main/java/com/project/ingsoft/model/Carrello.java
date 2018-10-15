package com.project.ingsoft.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.ingsoft.exceptions.carrelloException;

@Entity
@Table(name="carrello")
public class Carrello implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="user_id")
	private Integer user_id;
	
	@Column(name="evento_id")
	private Integer evento_id;
	
	public Carrello() {}
	
	public Carrello(Integer user_id, Integer evento_id) throws carrelloException {
		this.setUser_id(user_id);
		this.setEvento_id(evento_id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) throws carrelloException {
		if(user_id < 0)
			throw new carrelloException("UserID non valido");
		
		this.user_id = user_id;
	}

	public Integer getEvento_id() {
		return evento_id;
	}

	public void setEvento_id(Integer evento_id) throws carrelloException {
		if(evento_id < 0)
			 throw new carrelloException("EventoID non valido");
		
		this.evento_id = evento_id;
		
	}
	
	
	

}
