package com.project.ingsoft.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*
 * Author: Alessio Spina
 * Class: WebMvcConfig
 * Description: Implementation of PasswordEncoder that uses the BCrypt strong hashing function. 
 * 				Clients can optionally supply a "strength" (a.k.a. log rounds in BCrypt) and a SecureRandom instance. 
 * 				The larger the strength parameter the more work will have to be done (exponentially) to hash the passwords. 
 * 				The default value is 10.
 * */


@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

}