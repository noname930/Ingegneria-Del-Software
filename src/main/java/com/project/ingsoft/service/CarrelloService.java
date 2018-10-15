package com.project.ingsoft.service;

import java.util.List;

import com.project.ingsoft.model.Carrello;

public interface CarrelloService {
	
	public List<Carrello> findbyUserId(Integer user_id);
	
	public void deleteItemByEventoID(Integer evento_id);
	
	public void deleteItemByEventoANDUserID(Integer user_id, Integer evento_id);
	
	public boolean addItemCarrello(Integer user_id, Integer evento_id);


}
