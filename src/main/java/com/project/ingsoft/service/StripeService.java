package com.project.ingsoft.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.project.ingsoft.model.ChargeRequest;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;

@Service
public class StripeService {

	private String secretkey="sk_test_Qb2ogTiXuwyVagBoCCM5VOcE";
	
	@PostConstruct
	public void init() {
		Stripe.apiKey=secretkey;
	}
	
	public Charge charge(ChargeRequest chargeRequest, boolean desktop_or_mob,String token_card) 
		      throws AuthenticationException, InvalidRequestException,
		        APIConnectionException, CardException, APIException {
		        Map<String, Object> chargeParams = new HashMap<>();
		        chargeParams.put("amount", chargeRequest.getAmount());
		        chargeParams.put("currency", chargeRequest.getCurrency());
		        chargeParams.put("description", chargeRequest.getDescription());
		        chargeParams.put("receipt_email", chargeRequest.getStripeEmail());
		        if(desktop_or_mob) 
		        	chargeParams.put("source", chargeRequest.getStripeToken());
		        else 
		        	chargeParams.put("card", token_card);
		        
		        return Charge.create(chargeParams);
		    }
}
