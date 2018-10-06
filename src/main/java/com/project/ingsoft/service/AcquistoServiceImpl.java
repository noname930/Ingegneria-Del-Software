package com.project.ingsoft.service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ingsoft.model.Acquisto;
import com.project.ingsoft.model.Evento;
import com.project.ingsoft.repository.AcquistoRepository;



@Service("AcquistoService")
public class AcquistoServiceImpl implements AcquistoService{

	@Autowired
	AcquistoRepository ar;
	
	@Autowired
	EventoService evs;
	
	
	@Override
	public void saveAcquisto(Integer user_id, Integer evento_id) {
		// TODO Auto-generated method stub
		
		
		Acquisto e=new Acquisto();
		e.setUser_id(user_id);
		e.setEvento_id(evento_id);
		e.setCode(UUID.randomUUID().toString());
		ar.save(e);
		
	}


	@Override
	public List<Acquisto> findbyUserId(Integer user_id) {
		// TODO Auto-generated method stub
		return ar.findbyUserId(user_id);
	}


	@Override
	public HashMap<Evento, String> entityEventoAndAcquistoCode(Integer user_id) {
		
		HashMap<Evento,String> acquisti_eventi= new HashMap<Evento,String>();
		List<Acquisto> list_acquisti = ar.findbyUserId(user_id);
		List<Evento> list_eventi = evs.getmultiEventsbyID(list_acquisti);
		
		for(int i=0; i<list_eventi.size(); i++) {
			acquisti_eventi.put(list_eventi.get(i), list_acquisti.get(i).getCode());
		}
		
		return acquisti_eventi;
	}


	@Override
	public boolean validate_qrcode(String codice) {
			
		if(ar.validate_qrcode(codice)==null)	
			return false;
		else 
			return true;
	}

}
