package com.project.ingsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ingsoft.model.Carrello;


@Repository("CarrelloRepository")
public interface CarrelloRepository extends JpaRepository<Carrello,Integer> {
	
	
	//da fare
//	public Carrello findbyUserId(Integer user_id);

}
