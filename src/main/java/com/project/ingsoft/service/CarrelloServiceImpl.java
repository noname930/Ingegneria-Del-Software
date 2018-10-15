package com.project.ingsoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ingsoft.exceptions.carrelloException;
import com.project.ingsoft.model.Carrello;
import com.project.ingsoft.repository.CarrelloRepository;

@Service("CarrelloService")
public class CarrelloServiceImpl implements CarrelloService{

	@Autowired
	private CarrelloRepository CarrelloRep;
	
	@Autowired
	private EventoService eventoservice;
	
	@Autowired
	private UserService userService;
	
	
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
	public boolean addItemCarrello(Integer user_id, Integer evento_id) {
		
		if(user_id < 0 || evento_id < 0 || !userService.existUserID(user_id) || !eventoservice.checkExistsEventoID(evento_id)) {
			System.out.println("ADDITEMCARRELLO: "+ userService.existUserID(user_id) + " " + eventoservice.checkExistsEventoID(evento_id));
			return false;
		}
			
		Carrello c= new Carrello();
		try {
			c.setUser_id(user_id);
			c.setEvento_id(evento_id);
		} catch (carrelloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		CarrelloRep.save(c);
		return true;
		
	}


	@Override
	public void deleteItemByEventoANDUserID(Integer user_id, Integer evento_id) {
		// TODO Auto-generated method stub
		List<Carrello> lista_eventi= CarrelloRep.findbyEventoAndUserID(user_id, evento_id);
		
	   try {
		   CarrelloRep.delete(lista_eventi.get(0));
	   } catch(Exception e) {
		   e.printStackTrace();
	   }
		
	}
	
	

}
