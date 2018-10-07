package com.project.ingsoft.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.project.ingsoft.model.Acquisto;
import com.project.ingsoft.model.AcquistoUser;
import com.project.ingsoft.model.Carrello;
import com.project.ingsoft.model.ChargeRequest;
import com.project.ingsoft.model.ChargeRequest.Currency;
import com.project.ingsoft.model.Evento;
import com.project.ingsoft.model.User;
import com.project.ingsoft.model.User_Role;
import com.project.ingsoft.repository.EventoRepository;
import com.project.ingsoft.repository.RoleRepository;
import com.project.ingsoft.repository.UserRepository;
import com.project.ingsoft.repository.UserRoleRepository;
import com.project.ingsoft.service.AcquistoService;
import com.project.ingsoft.service.CarrelloService;
import com.project.ingsoft.service.EventoService;
import com.project.ingsoft.service.ImageService;
import com.project.ingsoft.service.RoleService;
import com.project.ingsoft.service.StripeService;
import com.project.ingsoft.service.UserRoleService;
import com.project.ingsoft.service.UserService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.sun.mail.iap.Response;

@Controller
public class desktopController {
	
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
		List<Evento> lista_concerti = eventoservice.getbyTipologia("concerto");
		List<Evento> lista_fiere = eventoservice.getbyTipologia("fiera");
		List<Evento> lista_sports = eventoservice.getbyTipologia("sport");
		List<Evento> lista_teatri = eventoservice.getbyTipologia("teatro");
		List<Evento> lista_cinema = eventoservice.getbyTipologia("cinema");
		
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
	public ModelAndView infoEvento (@PathVariable Integer id,Principal principal) { //[ il controller ha come parametro l'ID dell'evento che verrà
		ModelAndView mav = new ModelAndView();					//passato nell'indirizzo. Es www.sito/evento/7 ==> id=7 ]
		mav.setViewName("infoEvento.html");
		System.out.println(id);
		Evento evento = eventoservice.getbyID(id);// [ funzione dao che seleziona l'evento tramite l'id ]
		mav.addObject("evento", evento); // [ aggiunto l'evento selezionato alla view in modo tale da poter essere
										// visualizzato ]
		try {
		
			User u=userService.findByUsername(principal.getName()); 
			mav.addObject("user", u);
		    mav.addObject("amount", 40 * 100); // in cents
		    mav.addObject("stripePublicKey", stripePublicKey);
		    mav.addObject("currency", ChargeRequest.Currency.EUR);			
		}
		catch (NullPointerException e) {
			
			e.printStackTrace();
		}
		
		
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
	
	
	
	
	@RequestMapping(value="/registration", method=RequestMethod.GET)
	public ModelAndView registration(User u,BindingResult bindingresult) {
		ModelAndView mav = new ModelAndView();	
	    mav.setViewName("registration.html");
	    //System.out.println("pass:["+u.getPassword()+"]"+" => " + "["+bCryptPasswordEncoder.encode(u.getPassword())+"]");
	    
	    
	    u.setPassword(bCryptPasswordEncoder.encode(u.getPassword())); // encrypt della pass inserita dall'user
	    u.setEnabled(1); // abilito l'account
	    userService.saveUser(u); // salvo l'account	    
	    //System.out.println(u.getId()+" "+ (roleRepository.findByRole("admin").getId()));
	    urService.insertAdminRole(u,roleService.findbyRole("admin"), new User_Role()); //affido il ruolo admin al nuovo account
	    
		return mav;
	}
	
	
	@RequestMapping(value="/userpage", method=RequestMethod.GET)
	public ModelAndView request_userinfo(Principal principal) {
		
		ModelAndView mav = new ModelAndView();	
		mav.setViewName("userPage.html");
		
		User u=userService.findByUsername(principal.getName());  // la classe Principal, è importata da Spring Security e permette di poter
		mav.addObject("user", u);									// identificare l'username dell'user che ha effettuato l'accesso
					
		
		return mav;
	}
	
	
	@RequestMapping(value="/carrello", method=RequestMethod.GET)
	public ModelAndView carrello(Principal principal) {
		ModelAndView mav = new ModelAndView();	
		mav.setViewName("carrello.html");
		
		
		try {
			
			User u=userService.findByUsername(principal.getName()); 
			mav.addObject("user", u);	
			//List<Carrello> carrelloItems=carrService.findbyUserId(u.getId()); //insieme oggetti di tipo "carrello" che rappresentano gli item scelti dall'user
		//	List<Evento> listEventi=eventoservice.getmultiEventsbyIDs(carrelloItems); //ad ogni oggetto di tipo "carrello" riesco ad individuare il corrispettivo evento scelto
			List<Evento> listEventi=eventoservice.getEventiofCarrelloUser(u.getId());
			System.out.println(listEventi.toString());
			//User u=userService.findByUsername(principal.getName()); 
			mav.addObject("user", u);
		    mav.addObject("amount", 40 * 100); // in cents
		    mav.addObject("stripePublicKey", stripePublicKey);
		    mav.addObject("currency", ChargeRequest.Currency.EUR);			
			
			
			mav.addObject("eventi",listEventi);
		}
		catch (NullPointerException e) {
			
			e.printStackTrace();
		}	
		
		
		
		return mav;
	}
	
	
	@RequestMapping(value="/carrello/delete/item/{id}", method=RequestMethod.GET)
	public ModelAndView deleteCarrelloItem(@PathVariable Integer id, Principal principal)
	{
		ModelAndView mav = new ModelAndView();	
		
		try {
			User u=userService.findByUsername(principal.getName()); 
			carrService.deleteItemByEventoANDUserID(u.getId(),id);
			mav.setViewName("redirect:/carrello");
			
		}
		catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		return mav;
	}
	
	
	
	@RequestMapping(value="/carrello/add/item/{id}", method=RequestMethod.GET)
	public ModelAndView addCarrelloItem(@PathVariable Integer id, Principal principal)
	{
		ModelAndView mav = new ModelAndView();	
		
		try {
			User u=userService.findByUsername(principal.getName()); 
			carrService.addItemCarrello(u.getId(), id);
			mav.setViewName("redirect:/home");
			
		}
		catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		return mav;
	}
	
	
	
	
	@RequestMapping(value="/acquisto/response/{evento_id}", method=RequestMethod.POST)
	public ModelAndView charge(@PathVariable Integer evento_id,ChargeRequest chargeRequest, Model model, Principal principal) throws StripeException {
		ModelAndView mav = new ModelAndView();
		
		try {
			User u=userService.findByUsername(principal.getName()); 
			chargeRequest.setDescription("Example charge");
			chargeRequest.setCurrency(Currency.EUR);
			Charge charge = paymentsService.charge(chargeRequest,true,null);
			model.addAttribute("id", charge.getId());
			model.addAttribute("status", charge.getStatus());
			model.addAttribute("chargeId", charge.getId());
			model.addAttribute("balance_transaction", charge.getBalanceTransaction());
			mav.addObject("error",0);
			Evento e=eventoservice.getbyID(evento_id);
			mav.addObject("evento",e);
			
			acqService.saveAcquisto(u.getId(),evento_id);
			carrService.deleteItemByEventoANDUserID(u.getId(), evento_id);
			
			
					
		} catch(NullPointerException e) {
		   mav.addObject("error",1);
			
			
		}
		
		mav.setViewName("acquisto_response.html");
		return mav;
	}
	
	@ExceptionHandler(StripeException.class)
	public String handleError(Model model, StripeException ex) {
		model.addAttribute("error", ex.getMessage());
		return "acquisto_response.html";
	}
	
	
	
	@RequestMapping(value="/usershopping", method=RequestMethod.GET)
	public ModelAndView usershopping(Principal principal) {
		ModelAndView mav = new ModelAndView();	
		mav.setViewName("usershopping.html");
		
		
		try {
			
			User u=userService.findByUsername(principal.getName()); 
			mav.addObject("user", u);	
		//	List<Acquisto> Items=acqService.findbyUserId(u.getId()); //insieme oggetti di tipo "acquisto" che rappresentano gli item scelti dall'user
		//	List<Evento> listEventi=eventoservice.getmultiEventsbyID(Items); //ad ogni oggetto di tipo "carrello" riesco ad individuare il corrispettivo evento scelto
			List<AcquistoUser> acquisti_user=eventoservice.getAcquistiOfUser(u.getId());
			System.out.println(acquisti_user.toString());
			
			
			//mav.addObject("eventi",listEventi);
			mav.addObject("eventi",acquisti_user);
		}
		catch (NullPointerException e) {
			
			e.printStackTrace();
		}	
		
		
		
		return mav;
	}
	

	@RequestMapping(value="/QRcode/{codice}", method=RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> qrcode(@PathVariable String codice, Principal principal) {
		try {
			
			User u=userService.findByUsername(principal.getName());
			

			return ResponseEntity.ok().cacheControl(CacheControl.maxAge(30, TimeUnit.MINUTES))
					.body(imageService.generateQRCodeAsync(codice, 400, 400).get());
		}
		catch(Exception e ) {
			e.printStackTrace();
			
			
		}
		return null;
	}
	
	

	@RequestMapping(value="/validate/qrcode/{codice}", method=RequestMethod.GET)
	public ResponseEntity<String> validate_qrcode(@PathVariable String codice, Principal principal) {
		try {
			//verificare se l'account loggato è un operatore
			if(acqService.validate_qrcode(codice))
				return new ResponseEntity<String>("FOUND", HttpStatus.FOUND);
			else
				return new ResponseEntity<String>("NOT FOUND",HttpStatus.NOT_FOUND);		
		}
		catch(NullPointerException e ) {
			e.printStackTrace();			
			
		}
		return null;
	}
	
	
	
}
