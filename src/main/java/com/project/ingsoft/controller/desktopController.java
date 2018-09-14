package com.project.ingsoft.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.ingsoft.model.Evento;
import com.project.ingsoft.model.User;
import com.project.ingsoft.repository.EventoRepository;

@Controller
public class desktopController {
	
	@Autowired
	private EventoRepository eventorepository;
	
	
	//============= CONTROLLER CHE GESTICE LE RICHIESTE SULL'INDIRIZZO {sitoweb/} ==============
	@RequestMapping(value= {"/","/home"}, method=RequestMethod.GET)
	public ModelAndView home(HttpSession httpSession)
	{
		ModelAndView mav = new ModelAndView(); //creo un nuovo oggetto ModelAndView
		mav.setViewName("home.html"); // setto la pagina home.html da visualizzare
		
		//String pass="spina";
		//System.out.println("prova:["+bCryptPasswordEncoder.encode(pass)+"]");
		System.out.println("Session id: " + httpSession.getId());
		
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
	
	
	//========== CONTROLLER CHE GESTISCE LE RICHIESTE INFO DEGLI EVENTI ========
	@RequestMapping(value="/evento/{id}", method=RequestMethod.GET)
	public ModelAndView infoEvento (@PathVariable Integer id) { //[ il controller ha come parametro l'ID dell'evento che verrà
		ModelAndView mav = new ModelAndView();					//passato nell'indirizzo. Es www.sito/evento/7 ==> id=7 ]
		mav.setViewName("infoEvento.html");
		Evento evento = eventorepository.getOne(id); // [ funzione dao che seleziona l'evento tramite l'id ]
		mav.addObject("evento", evento); // [ aggiunto l'evento selezionato alla view in modo tale da poter essere
										// visualizzato ]
		return mav;
	}
	
	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();	
		mav.setViewName("login.html");
		
		//========= aggiungo un oggetto di tipo user alla view ================
		User user=new User();
		User user_reg=new User();
		mav.addObject("user", user);
		mav.addObject("user_reg", user_reg);
		
		return mav;
	}
	
	
	
	@RequestMapping(value="/userpage", method=RequestMethod.GET)
	public ModelAndView userpage() {
		ModelAndView mav = new ModelAndView();	
		mav.setViewName("userPage.html");
		
		
		return mav;
	}
	
	
	
	@RequestMapping(value="/usershopping", method=RequestMethod.GET)
	public ModelAndView usershopping() {
		ModelAndView mav = new ModelAndView();	
		mav.setViewName("acquistiUser.html");
		
		
		return mav;
	}
	
	@RequestMapping(value="/registration", method=RequestMethod.POST)
	public ModelAndView registration() {
		ModelAndView mav = new ModelAndView();	
		mav.setViewName("userPage.html");
		
		
		return mav;
	}
	
	
}
