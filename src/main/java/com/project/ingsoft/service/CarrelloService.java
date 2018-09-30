package com.project.ingsoft.service;

import java.util.List;

import com.project.ingsoft.model.Carrello;

public interface CarrelloService {
	
	public List<Carrello> findbyUserId(Integer user_id);


}
