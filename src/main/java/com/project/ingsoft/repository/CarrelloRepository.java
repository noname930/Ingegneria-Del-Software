package com.project.ingsoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.ingsoft.model.Carrello;




@Repository("CarrelloRepository")
public interface CarrelloRepository extends JpaRepository<Carrello,Integer> {
	
	
	@Query("SELECT c FROM Carrello c WHERE c.user_id=?1")
	public List<Carrello> findbyUserId(Integer user_id);
	

	@Query("SELECT c FROM Carrello c WHERE c.evento_id=?1")
	public List<Carrello> findbyEventoId(Integer evento_id);
	
	@Query("SELECT c FROM Carrello c WHERE c.user_id=?1 AND c.evento_id=?2")
	public List<Carrello> findbyEventoAndUserID(Integer user_id, Integer evento_id);

}
