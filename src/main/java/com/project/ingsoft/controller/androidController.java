package com.project.ingsoft.controller;



import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.ingsoft.model.AcquistoUser;
import com.project.ingsoft.model.Carrello;
import com.project.ingsoft.model.Evento;
import com.project.ingsoft.model.User;
import com.project.ingsoft.repository.EventoRepository;
import com.project.ingsoft.repository.UserRepository;
import com.project.ingsoft.service.AcquistoService;
import com.project.ingsoft.service.CarrelloService;
import com.project.ingsoft.service.EventoService;
import com.project.ingsoft.service.ImageService;
import com.project.ingsoft.service.RoleService;
import com.project.ingsoft.service.StripeService;
import com.project.ingsoft.service.UserRoleService;
import com.project.ingsoft.service.UserService;
;



@Controller
public class androidController {
	
	@Autowired
	EventoRepository eventorepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private EventoService eventoservice;
	
	@Autowired
	private UserRoleService urService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired // entità che cripta le password
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CarrelloService carrService;
	
	@Autowired
	private StripeService paymentsService;
	
	@Autowired
	private AcquistoService acqService;
	
	@Autowired
	private ImageService imageService;
	
	private String stripePublicKey="pk_test_SnryBHIG3it0bwxVO9ilP3R4";

	
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
	
	@RequestMapping(value="/api/get/carrello")
	public @ResponseBody List<Evento> getCarrello(Principal principal) {
		User u = userRepository.findByUsername(principal.getName());
	//	List<Carrello> carrelloItems=carrService.findbyUserId(u.getId()); 
	//	List<Evento> listEventi=eventoservice.getmultiEventsbyIDs(carrelloItems); 
		List<Evento> listEventi=eventoservice.getEventiofCarrelloUser(u.getId());
		return listEventi;
	}

	
	@RequestMapping(value="/api/get/acquisti")
	public @ResponseBody List<AcquistoUser> getAcquisti(Principal principal){
		User u = userRepository.findByUsername(principal.getName());
		return eventoservice.getAcquistiOfUser(u.getId());

	}
	
	
	
	
}
