package com.revature.services;

import org.springframework.stereotype.Component;

@Component("contactTheDao")
public class ContactTheDao {

	public Boolean telephone(Long phoneNumber, String message){
	
		System.out.println("Inside telephone with argurmets " + phoneNumber + " and " + message);
		return true;
		//return "Calling #" + phoneNumber + " saying " + message +"... they say hello";
	
	}
	
	public short hangup(){
		
		System.out.println("you hung up");
		return (short)2;
	}
	
}
