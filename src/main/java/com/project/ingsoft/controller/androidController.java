package com.project.ingsoft.controller;



import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.ingsoft.model.Evento;
import com.project.ingsoft.model.User;
import com.project.ingsoft.repository.EventoRepository;
import com.project.ingsoft.repository.UserRepository;
;



@Controller
public class androidController {
	
	@Autowired
	EventoRepository eventorepository;
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value="/get/eventi")
	public @ResponseBody List<Evento> getEventi() {
		
		 return (List<Evento>)eventorepository.findAll();
		
	}
	
	
	@RequestMapping(value="/api/login", method=RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();	
		mav.setViewName("login_app.html");
		
		//========= aggiungo un oggetto di tipo user alla view ================
		User user=new User();
		User user_reg=new User();
		mav.addObject("user", user);
		mav.addObject("user_reg", user_reg);
		
		return mav;
	}
	
	
	
	@RequestMapping(value="/api/get/userinfo")
	public @ResponseBody User getinfoaccount(Principal principal) {
			
		return userRepository.findByUsername(principal.getName());
	}
	
	
	
	
	
}
