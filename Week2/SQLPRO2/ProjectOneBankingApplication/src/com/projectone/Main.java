package com.projectone;

import org.apache.log4j.Logger;


public class Main {
	static final Logger customLogger = Logger.getRootLogger();
	  
	public static void main(String[] args) {
		
		
		
		
BankDriver bd = new BankDriver();
	bd.runner();

	}

}
