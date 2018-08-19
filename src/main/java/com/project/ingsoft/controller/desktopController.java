package com.project.ingsoft.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.ingsoft.model.Evento;
import com.project.ingsoft.repository.EventoRepository;

@Controller
public class desktopController {
	
	@Autowired
	private EventoRepository eventorepository;
	
	//============= CONTROLLER CHE GESTICE LE RICHIESTE SULL'INDIRIZZO {sitoweb/} ==============
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView home()
	{
		ModelAndView mav = new ModelAndView(); //creo un nuovo oggetto ModelAndView
		mav.setViewName("home.html"); // setto la pagina home.html da visualizzare
		
		//========== creazione liste di eventi in base alle tipologie ================
		List<Evento> lista_concerti = eventorepository.getbyTipologia("concerto");
		List<Evento> lista_fiere = eventorepository.getbyTipologia("fiera");
		List<Evento> lista_sports = eventorepository.getbyTipologia("sport");
		List<Evento> lista_teatri = eventorepository.getbyTipologia("teatro");
		List<Evento> lista_cinema = eventorepository.getbyTipologia("cinema");
		
		// ========= aggiungo le liste di oggetti creati alla pagina web ============
		mav.addObject("lista_concerti", lista_concerti);
		mav.addObject("lista_fiere", lista_fiere);
		mav.addObject("lista_sports", lista_sports);
		mav.addObject("lista_teatri", lista_teatri);
		mav.addObject("lista_cinema", lista_cinema);
		
		return mav;
	}
	
	@RequestMapping(value="/evento/{id}", method=RequestMethod.GET)
	public ModelAndView infoEvento (@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("infoEvento.html");
		Evento evento = eventorepository.getOne(id);
		System.out.println("id: " + evento.getId());
		mav.addObject("evento", evento);
		return mav;
	}
	
}
