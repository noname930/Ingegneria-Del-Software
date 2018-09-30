package com.project.ingsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ingsoft.model.User;

/*
 * Author: Alessio Spina
 * Interface: UserRepository
 * Description: A Repository is essentially a facade for persistence that uses Collection style semantics (Add, Update, Remove) 
 * 				to supply access to data/objects. It is a way of decoupling the way you store data/objects from the rest of the 
 * 				application. 
 * 				
 * 				Un repository è essenzialmente una facciata per la persistenza che utilizza la semantica dello stile Collection 
 * 				(Aggiungi, Aggiorna, Rimuovi) per fornire accesso a dati / oggetti. 
 * 				È un modo per disaccoppiare la memorizzazione memorizzi dati / oggetti dal resto dell'applicazione.
 * */

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User,Integer> {
	
	//[N.B.] Metodi per aggiunta,aggiornamento e rimozione User già sono stati 
	//		 ereditati da JpaRepository
	
	//Metodo per cercare un User tramite il suo username.
	public User findByUsername(String username);
	

}
