package com.project.ingsoft.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.ingsoft.model.Evento;
import com.project.ingsoft.repository.EventoRepository;
;



@Controller
public class androidController {
	
	@Autowired
	EventoRepository eventorepository;
	
	@RequestMapping(value="/get/eventi")
	public @ResponseBody List<Evento> getEventi() {
		
		 return (List<Evento>)eventorepository.findAll();
		
	}
	
	
	
}
