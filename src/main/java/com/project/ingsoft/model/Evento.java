package com.project.ingsoft.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

/*
 * Author: Alessio Spina
 * Class: Evento
 * Description: An entity is annotated as @Entity, and has a special relationship with your database - generally 
 * 				each instance of an entity corresponds to a single row and the class itself corresponds to the table 
 * 				in which those rows are stored. In any case, we supply annotations for persistence on our entity classes. 
 * 				[Evento is a Entity who rappresents a list of Events situated in the table "evento" of DB.]
 * 				
 * 				Un'entità è annotata come @Entity e ha una relazione speciale con il tuo database - in generale
 * 				ogni istanza di un'entità corrisponde a una singola riga e la classe stessa corrisponde alla tabella
 * 				in cui sono archiviate tali righe.
 * */


@Entity
@Table(name="evento")
public class Evento implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="localita")
	private String localita;
	
	@Column(name="data")
	private Date data;
	
	@Column(name="costo")
	private float costo;
	
	@Column(name="tipologia")
	private String tipologia;
	
	@Column(name="img")
	private String img;
	
	@Column(name="indirizzo")
	private String indirizzo;
	
	@Column(name="descrizione")
	private String descrizione;
	
	@Column(name="sotto_tipologia")
	private String sotto_tipologia;
	
	

	public String getSotto_tipologia() {
		return sotto_tipologia;
	}

	public void setSotto_tipologia(String sotto_tipologia) {
		this.sotto_tipologia = sotto_tipologia;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	
	
	
}

