package com.project.ingsoft.service;

import com.project.ingsoft.model.User;


/*
 * Author: Alessio Spina
 * Interface: UserService
 * Description: A service supplies coordination or other "services" that are required to operate your application. 
 * 				They are very different in that Services don't typically know how to access data from persistence, 
 * 				and repositories typically only access data/objects for any services you may have.
 * 				
 * 				Un servizio fornisce il coordinamento o altri "servizi" necessari per il funzionamento dell'applicazione. 
 * 				Sono molto diversi in quanto i Servizi in genere non sanno come accedere ai dati dalla persistenza e gli archivi 
 * 				in ​​genere accedono solo a dati / oggetti per qualsiasi servizio che si possa avere.
 * */


public interface UserService {
	
	public User findByUsername(String username);
	
	public void saveUser(User a);
	
	public boolean existUsername(String username);
	
	public boolean existUserID(Integer user_id);


}
