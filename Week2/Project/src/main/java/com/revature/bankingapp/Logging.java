package com.revature.bankingapp;

import org.apache.log4j.Logger;

public class Logging {
	private static Logger getLogger(){
		return Logger.getLogger("BankingApp");
	}
	
	public static void info(Object o, Throwable t){
		getLogger().info(o, t);
	}
	
	public static void info(Object o){
		getLogger().info(o);
	}
	
	public static void debug(Object o, Throwable t){
		getLogger().debug(o, t);
	}
	
	public static void error(Object o){
		getLogger().error(o);
	}
	
	public static void error(Object o, Throwable t){
		getLogger().error(o, t);
	}
	public static void warn(Object o){
		getLogger().warn(o);
	}
	public static void warn(Object o, Throwable t){
		getLogger().warn(o, t);
	}
	public static void fatal(Object o){
		getLogger().fatal(o);
	}
	public static void fatal(Object o, Throwable t){
		getLogger().fatal(o, t);
	}
	public static void falalNoConnection(Object o, Throwable t){
		getLogger().fatal(o, t);
		
	}
	
}
