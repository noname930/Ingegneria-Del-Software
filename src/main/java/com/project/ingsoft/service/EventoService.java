package com.project.ingsoft.service;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.project.ingsoft.model.Evento;

/*
 * Author: Alessio Spina
 * Interface: EventoService
 * Description: A service supplies coordination or other "services" that are required to operate your application. 
 * 				They are very different in that Services don't typically know how to access data from persistence, 
 * 				and repositories typically only access data/objects for any services you may have.
 * 				
 * 				Un servizio fornisce il coordinamento o altri "servizi" necessari per il funzionamento dell'applicazione. 
 * 				Sono molto diversi in quanto i Servizi in genere non sanno come accedere ai dati dalla persistenza e gli archivi 
 * 				in ​​genere accedono solo a dati / oggetti per qualsiasi servizio che si possa avere.
 * 
 * */

public interface EventoService {
	
	public void saveEvento(Evento e);
	
	public List<Evento> getbyDate(Date date);
	
	public List<Evento> getbyTipologia(String Tipologia);
		
	public List<Evento> getbyCosto(float costo);

}
