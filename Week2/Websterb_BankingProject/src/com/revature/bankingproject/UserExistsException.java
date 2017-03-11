package com.revature.bankingproject;

public class UserExistsException extends Exception{
	public UserExistsException(String message){
		super(message);
	}
}
