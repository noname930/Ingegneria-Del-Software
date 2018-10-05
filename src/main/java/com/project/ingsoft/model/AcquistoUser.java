package com.project.ingsoft.model;

import java.sql.Date;

public class AcquistoUser {
	
	private Integer evento_id;
	private String nome_evento;
	private Date data;
	private float costo;
	private String localita;
	private String tipologia;
	private String sotto_tipologia;
	private String indirizzo;
	private String img;
	private Integer user_id;
	private String acquisto_code;
	
/*	public AcquistoUser(Evento e, Acquisto a, Integer user_id) {
		this.evento_id=e.getId();
		this.nome_evento=e.getNome();
		this.data=e.getData();
		this.costo=e.getCosto();
		this.localita=e.getLocalita();
		this.tipologia=e.getTipologia();
		this.sotto_tipologia=e.getSotto_tipologia();
		this.indirizzo=e.getIndirizzo();
		this.img=e.getImg();
		this.user_id=user_id;
		this.acquisto_code=a.getCode();
		
	}*/
	
	public AcquistoUser(Integer evento_id, String nome_evento, Date data, float costo, String localita, String tipologia, 
						String sotto_tipologia, String indirizzo, String img, Integer user_id, String acquisto_code) {
		
		this.evento_id=evento_id;
		this.nome_evento=nome_evento;
		this.data=data;
		this.costo=costo;
		this.localita=localita;
		this.tipologia=tipologia;
		this.sotto_tipologia=sotto_tipologia;
		this.indirizzo=indirizzo;
		this.img=img;
		this.user_id=user_id;
		this.acquisto_code=acquisto_code;		
	}


	public Integer getEvento_id() {
		return evento_id;
	}

	public void setEvento_id(Integer evento_id) {
		this.evento_id = evento_id;
	}

	public String getNome_evento() {
		return nome_evento;
	}

	public void setNome_evento(String nome_evento) {
		this.nome_evento = nome_evento;
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

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getAcquisto_code() {
		return acquisto_code;
	}

	public void setAcquisto_code(String acquisto_code) {
		this.acquisto_code = acquisto_code;
	}
	
	
	
}
