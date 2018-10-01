package com.project.ingsoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ingsoft.model.Carrello;
import com.project.ingsoft.repository.CarrelloRepository;

@Service("CarrelloService")
public class CarrelloServiceImpl implements CarrelloService{

	@Autowired
	private CarrelloRepository CarrelloRep;
	
	
	@Override
	public List<Carrello> findbyUserId(Integer user_id) {
		
		return CarrelloRep.findbyUserId(user_id);
	}


	@Override
	public void deleteItemByEventoID(Integer evento_id) {
		
		List<Carrello> lista_eventi= CarrelloRep.findbyEventoId(evento_id);
		
		CarrelloRep.delete(lista_eventi.get(0));
		
		
	}


	@Override
	public void addItemCarrello(Integer user_id, Integer evento_id) {
		
		Carrello c= new Carrello();
		c.setUser_id(user_id);
		c.setEvento_id(evento_id);
		CarrelloRep.save(c);
		
	}


	@Override
	public void deleteItemByEventoANDUserID(Integer user_id, Integer evento_id) {
		// TODO Auto-generated method stub
		List<Carrello> lista_eventi= CarrelloRep.findbyEventoAndUserID(user_id, evento_id);
		CarrelloRep.delete(lista_eventi.get(0));
		
	}
	
	

}
