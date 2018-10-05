package com.project.ingsoft.service;

import java.util.HashMap;
import java.util.List;

import com.project.ingsoft.model.Acquisto;
import com.project.ingsoft.model.Evento;


public interface AcquistoService {
		
	public void saveAcquisto(Integer user_id, Integer evento_id);
	
	public List<Acquisto> findbyUserId(Integer user_id);
	
	public HashMap<Evento,String> entityEventoAndAcquistoCode(Integer user_id);

}
