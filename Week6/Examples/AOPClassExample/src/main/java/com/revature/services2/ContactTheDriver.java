package com.revature.services2;

import org.springframework.stereotype.Component;

@Component("imposterContacter")
public class ContactTheDriver {

	public Boolean telephone(int phoneNumber, String message){
	
		System.out.println("Inside fake telephone with argurmets " + phoneNumber + " and " + message);
		return false;
		//return "Calling #" + phoneNumber + " saying " + message +"... they say hello";
	
	}
	
}
