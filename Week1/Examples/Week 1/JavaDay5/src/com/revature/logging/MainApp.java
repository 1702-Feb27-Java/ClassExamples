package com.revature.logging;

import org.apache.log4j.Logger;

public class MainApp {
	
	static final Logger l = Logger.getRootLogger();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i = (int)Math.random()*100;
		
		if ( i < 59){
			System.out.println("Bad grade.");
			l.trace("This is the least concernned level of a log");
			l.debug("Typically used for debugging");
			l.info("Use when you wish to provide info about a section of the code");
			l.warn("Possible chance of program failure in this section");
			l.error("Error in code at this point, usually terminates program");
			l.fatal("This level of log usually reserved for unrecoverable issues with program");
			
		}
		else{
			System.out.println("Grade");
		}
	}

}
