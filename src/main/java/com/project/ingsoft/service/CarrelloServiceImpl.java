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
	
	

}
