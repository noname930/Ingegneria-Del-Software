package com.project.ingsoft.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/*
 * Author: Alessio Spina
 * Class: SecurityConfiguration
 * Description: The SecurityConfig will: require authentication to every URL in your application, generate a login form for you
				allow the user with the Username user and the Password password to authenticate with form based authentication,
				allow the user to logout, CSRF attack prevention and more...
 * */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired // entità che cripta le password
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired // Connessione al Database
	private DataSource dataSource;
	
	// query prelevata da application.properties che seleziona tutti users presenti del db
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	// query prelevata da application.properties che seleziona tutti i ruoli presenti nel db
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	
	@Override // ===== Configurazione dell'autenticazione ==========
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.
			jdbcAuthentication()
				.usersByUsernameQuery(usersQuery)
				.authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource)
				.rolePrefix("ROLE_")
				.passwordEncoder(bCryptPasswordEncoder);
		
	}

	@Override // ===== Configurazione accesso alla pagina web ==========
	protected void configure(HttpSecurity http) throws Exception {		

	http.csrf().disable()
        .authorizeRequests()
        	.antMatchers("/").permitAll() //accesso alla permesso a tutti
        	.antMatchers("/registration").permitAll()
        	.antMatchers("/userpage", "/usershopping").hasAuthority("admin")
        	.anyRequest().authenticated()// autentico la richiesta di login
            .and()
         .formLogin()
            .loginPage("/login")
         //   .defaultSuccessUrl("/home") // login approvato => va in home
         //   .failureUrl("/login.html?error=true")
            .successHandler(successHandler()).defaultSuccessUrl("/home")
            .failureHandler(failureHandler())
            .permitAll()
            .and()
        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/home");
            
		
		http.authorizeRequests().antMatchers("/resources/**").permitAll().anyRequest().permitAll(); // escludo le risorse dal controllo

	}
	
	private AuthenticationSuccessHandler successHandler() {
	    return new AuthenticationSuccessHandler() {
	      @Override
	      public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
	        httpServletResponse.getWriter().append("OK");
	        httpServletResponse.setStatus(200);
	      }
	    };
	  }
	
	  private AuthenticationFailureHandler failureHandler() {
		    return new AuthenticationFailureHandler() {
		    
		      
		    @Override
		      public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		    	httpServletResponse.getWriter().append("Authentication failure");
		        httpServletResponse.setStatus(401);
		        //httpServletResponse.sendRedirect("/login");
		      }
		    };
		  }
}
