package com.project.ingsoft.model;

import java.io.Serializable;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.ingsoft.exceptions.CredentialsException;
import com.project.ingsoft.exceptions.MailNotValid;
import com.project.ingsoft.exceptions.dataException;
import com.project.ingsoft.exceptions.passwordException;
import com.project.ingsoft.exceptions.phonenumberException;



/*
 * Author: Alessio Spina
 * Class: User
 * Description: An entity is annotated as @Entity, and has a special relationship with your database - generally 
 * 				each instance of an entity corresponds to a single row and the class itself corresponds to the table 
 * 				in which those rows are stored. In any case, we supply annotations for persistence on our entity classes. 
 * 				[User is a Entity who rappresents a list of accounts situated in the table "User" of DB.]
 * 				
 * 				Un'entità è annotata come @Entity e ha una relazione speciale con il tuo database - in generale
 * 				ogni istanza di un'entità corrisponde a una singola riga e la classe stessa corrisponde alla tabella
 * 				in cui sono archiviate tali righe.
 * */

@Entity
@Table(name="user")
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	

	@Column(name="password")
	private String password;
	

	@Column(name="username")
	private String username;
	

	@Column(name="enabled")
	private Integer enabled;
	

	@Column(name="nome")
	private String nome;
	

	@Column(name="cognome")
	private String cognome;
	

	@Column(name="data_di_nascita")
	private Date data;

	@Column(name="paese")
	private String paese;
	

	@Column(name="telefono")
	private String telefono;
	
	public User () {}
	
	public User(String nome, String cognome, String username, String password, Date data, String paese, String telefono, Integer enable) throws MailNotValid, 
																																				dataException, 
																																				passwordException, 
																																				phonenumberException, 
																																				CredentialsException {
		this.setNome(nome);
		this.setCognome(cognome);
		this.setUsername(username);
		this.setPassword(password);
		this.setData(data);
		this.setPaese(paese);
		this.setTelefono(telefono);
		this.setEnabled(enable);
	}
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws CredentialsException {
		if(nome.length()==0 || !checkCredentials(nome))
			throw new CredentialsException("Nome inserito non valido. \nN.B. Spazi e Numeri non sono accettati");
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) throws CredentialsException {
		if(cognome.length()==0 || !checkCredentials(cognome))
			throw new CredentialsException("Cognome inserito non valido. \nN.B. Spazi e Numeri non sono accettati");
		this.cognome = cognome;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) throws dataException {
		java.util.Date currentData = new java.util.Date();
		
		System.out.println(data.toString());
		System.out.println(currentData.toString());
		
		if(data.compareTo(currentData) >= 0) {
			throw new dataException("Data (" + data.toString()+")"+ " non  valida");
		}
		
		this.data = data;
	}

	public String getPaese() {
		return paese;
	}

	public void setPaese(String paese) {
		this.paese = paese;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) throws phonenumberException {
		if(telefono.length()!=10 || !checkPhoneNumber(telefono))
			throw new phonenumberException("Numero di telefono inserita non valido");

			
		this.telefono = telefono;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) throws MailNotValid {
		
		if(username != null) {
		 Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
         Matcher mat = pattern.matcher(username);		
         if(!mat.matches())
        	throw new MailNotValid("eMail non valida!");
		}
		
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws passwordException {
		if(password.length()<=5 || !checkPassword(password))
			throw new passwordException("Password inserita non valida");
		else
			this.password = password;
	}


	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	
	
	public boolean checkPassword(String password) {
		int count = 0;
		for(int i=0; i<password.length(); i++) {
			char x = password.charAt(i);
			
			if(Character.isWhitespace(x))
				break;
			else
				count++;
		}
		
		if(count == password.length())
			return true;
		else
			return false;
		
	}
	
	public boolean checkPhoneNumber(String telefono){
		
		int count = 0;
		for(int i=0; i<telefono.length(); i++) {
			char x = telefono.charAt(i);
			
			if(Character.isDigit(x))
				count++;
			else
				break;
		}
		
		if(count == telefono.length())
			return true;
		else
			return false;
		
	}
	
	public boolean checkCredentials(String name) {
		int count = 0;
		for(int i=0; i<name.length(); i++) {
			char x = name.charAt(i);
			
			if(Character.isJavaLetter(x))
				count++;
			else
				break;
		}
		
		if(count == name.length())
			return true;
		else
			return false;
	}

		
	
	

}
