package com.project.ingsoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ingsoft.model.User;
import com.project.ingsoft.repository.UserRepository;

/*
 * Author: Alessio Spina
 * Interface: UserServiceImpl
 * Description: A service supplies coordination or other "services" that are required to operate your application. 
 * 				They are very different in that Services don't typically know how to access data from persistence, 
 * 				and repositories typically only access data/objects for any services you may have.
 * 				
 * 				Un servizio fornisce il coordinamento o altri "servizi" necessari per il funzionamento dell'applicazione. 
 * 				Sono molto diversi in quanto i Servizi in genere non sanno come accedere ai dati dalla persistenza e gli archivi 
 * 				in ​​genere accedono solo a dati / oggetti per qualsiasi servizio che si possa avere.
 * */

@Service("UserService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository UserRep;
	

	@Override
	public User findByUsername(String username) {
		return UserRep.findByUsername(username);		
	}

	@Override
	public void saveUser(User a) {
		UserRep.save(a);
	}


}
