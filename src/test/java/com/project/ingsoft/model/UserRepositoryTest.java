package com.project.ingsoft.model;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.ingsoft.exceptions.*;
import com.project.ingsoft.repository.EventoRepository;
import com.project.ingsoft.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class UserRepositoryTest {
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository userRepository;
	
	private java.sql.Date dataSQL;
	private java.sql.Date dataSQL_wrong;
	
	@Before
	public void  init () throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
		String dateInString = "1993-06-14";
		java.util.Date dataUtil = sdf.parse(dateInString);
		dataSQL = new java.sql.Date(dataUtil.getTime());
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-M-dd");
		String dateInString2 = "2028-06-14";
		java.util.Date dataUtil2 = sdf.parse(dateInString2);
		dataSQL_wrong = new java.sql.Date(dataUtil2.getTime());
		
	}
	
	
	/*1) Test verifica d'inserimento di un user generico */
	@Test
	public void create_generic_user() throws passwordException, phonenumberException, CredentialsException {
		//creo l'istanza user
		try {
			entityManager.persist(new User("utenteprova","utenteprova","utente_prova@mail.com","utente_prova", dataSQL,"utente_prova","3935496480",1));
		
			//tramite accesso DAO all'entità User, estraggo l'user con username "utente_prova"
			User u = userRepository.findByUsername("utente_prova@mail.com");
			
			//se la stringa "utente_prova" sarà uguale all'username dell'user trovato in precedenza, allora il test sarà andato a buon fine
			assertEquals("utente_prova@mail.com",u.getUsername());
		} catch (MailNotValid | dataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/*2) Test: verifica che il metodo catturi correttamente l'eccezione prevista per il mancato rispetto del vincolo NOT NULL impostato sull'attributo username dell'entita
	 * User */
	@Test(expected = javax.persistence.PersistenceException.class)
	public void insert_users_with_username_null() throws passwordException, phonenumberException, CredentialsException {
		try {
			entityManager.persist(new User("utenteprova","utenteprova",null,"utente_prova",dataSQL,"utente_prova","3935496480",1));
		} catch (MailNotValid | dataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*3) Test: verifica che il metodo catturi correttamente l'eccezione prevista quando il vincolo PRIMARY KEY impostato sull'attributo username, non viene rispettato*/
	@Test(expected = javax.persistence.PersistenceException.class)
	public void insert_double_users_with_same_username() throws passwordException, phonenumberException, CredentialsException {
		try {
			entityManager.persist(new User("utenteprova","utenteprova","utente_prova@mail.com","utente_prova", dataSQL,"utente_prova","3935496480",1));
			entityManager.persist(new User("utenteprova","utenteprova","utente_prova@mail.com","utente_prova", dataSQL,"utente_prova","3935496480",1));
		} catch (MailNotValid | dataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*4) Test: inserimento di un username non valido. 
	 * [N.B.] si ricorda che per scelte tecniche l'username di un account deve essere sottoforma di mail*/
	@Test(expected = MailNotValid.class)
	public void insert_user_with_wrong_username() throws MailNotValid, passwordException, phonenumberException, CredentialsException {
		try {
			entityManager.persist(new User("utenteprova","utenteprova","utente_provamail.com","utente_prova", dataSQL,"Italia","3935496480",1));
		} catch (dataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*5) Test: inserimento di una data una data di nascita non valida*/
	@Test(expected = dataException.class)
	public void insert_user_with_wrong_date() throws MailNotValid, dataException, passwordException, phonenumberException, CredentialsException {
		entityManager.persist(new User("utenteprova","utenteprova","utente_prova@mail.com","utente_prova", dataSQL_wrong,"Italia","3935496480",1));	
	}
	
	
	/*6) Test: Inserimento di una password troppo piccola*/
	@Test(expected = passwordException.class)
	public void insert_wrong_password() throws MailNotValid, dataException, passwordException, phonenumberException, CredentialsException {
		entityManager.persist(new User("utente_prova","utenteprova","utente_prova@mail.com","", dataSQL,"Italia","3935496480",1));	
		
	}
	
	/*7) Test: Inserimento di una password con carattere spazio non accettato*/
	@Test(expected = passwordException.class)
	public void insert_wrong_password2() throws MailNotValid, dataException, passwordException, phonenumberException, CredentialsException {
		entityManager.persist(new User("utenteprova","utenteprova","utente_prova@mail.com","alessio 93", dataSQL,"Italia","3935496480",1));	
		
	}
	
	/*8) Test: Inserimento di un numero di telefono non valido*/
	@Test(expected = phonenumberException.class)
	public void insert_wrong_number () throws MailNotValid, dataException, passwordException, phonenumberException, CredentialsException {	
		entityManager.persist(new User("utenteprova","utenteprova","utente_prova@mail.com","password_prova", dataSQL,"Italia","00",1));	
	}
	
	/*9) Test: Inserimento di un nome non valido*/
	@Test(expected = CredentialsException.class)
	public void insert_wrong_name () throws MailNotValid, dataException, passwordException, phonenumberException, CredentialsException {
		entityManager.persist(new User("123jsjdsi12","utenteprova","utente_prova@mail.com","alessio_93", dataSQL,"Italia","3935496480",1));	
	}
	
	
	/*10) Test: Inserimento di un cognome non valido*/
	@Test(expected = CredentialsException.class)
	public void insert_wrong_surname () throws MailNotValid, dataException, passwordException, phonenumberException, CredentialsException {
		entityManager.persist(new User("utenteprova","9er9ger9","utente_prova@mail.com","alessio_93", dataSQL,"Italia","3935496480",1));	
	}
	

}
