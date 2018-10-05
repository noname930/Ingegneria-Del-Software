package com.project.ingsoft.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.ingsoft.model.AcquistoUser;
import com.project.ingsoft.model.Evento;

/*
 * Author: Alessio Spina
 * Interface: EventoRepository
 * Description: A Repository is essentially a facade for persistence that uses Collection style semantics (Add, Update, Remove) 
 * 				to supply access to data/objects. It is a way of decoupling the way you store data/objects from the rest of the 
 * 				application. 
 * 				
 * 				Un repository è essenzialmente una facciata per la persistenza che utilizza la semantica dello stile Collection 
 * 				(Aggiungi, Aggiorna, Rimuovi) per fornire accesso a dati / oggetti. 
 * 				È un modo per disaccoppiare la memorizzazione memorizzi dati / oggetti dal resto dell'applicazione.
 * */


@Repository("EventoRepository")
public interface EventoRepository extends JpaRepository<Evento,Integer> {
	
	@Query("SELECT t FROM Evento t WHERE t.data = ?1")
	public List<Evento> getbyDate(Date date);
	
	@Query("SELECT t FROM Evento t WHERE t.tipologia = ?1")
	public List<Evento> getbyTipologia(String Tipologia);
		
	@Query("SELECT t FROM Evento t WHERE t.costo = ?1")
	public List<Evento> getbyCosto(float costo);
	
	@Query("SELECT NEW com.project.ingsoft.model.AcquistoUser(E.id,E.nome,E.data,E.costo,E.localita,E.tipologia,E.sotto_tipologia,E.indirizzo,E.img,A.user_id,A.code) FROM Acquisto A INNER JOIN Evento E ON (A.evento_id=E.id)  WHERE A.user_id=?1")
	public List<AcquistoUser> getAcquistiOfUser(Integer user_id);
	
	@Query("SELECT e FROM Carrello c INNER JOIN Evento e ON (c.evento_id=e.id) WHERE c.user_id=?1")
	public List<Evento> getEventiofCarrelloUser(Integer user_id);
	
	
		
}
