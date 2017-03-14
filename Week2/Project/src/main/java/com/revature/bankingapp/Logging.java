package com.revature.bankingapp;

import org.apache.log4j.Logger;

import com.revature.bankingapp.database.service.Service;

public class Logging {
	
	private static Logger getLogger(){
		return Logger.getLogger("BankingApp");
	}
	
	public static void info(Object o, Throwable t){
		info(o, t, 0);
	}

	public static void info(Object o, Throwable t, int userId){
		getLogger().info(o, t);
		Service.getInstance().log(o.toString(), Service.getInstance().getInfoLogLevel(), userId);
		
	}
	
	public static void info(Object o){
		info(o, 0);
	}

	public static void info(Object o, int userId){
		getLogger().info(o);
		Service.getInstance().log(o.toString(), Service.getInstance().getInfoLogLevel(), userId);
		
	}
	
	public static void debug(Object o, Throwable t){
		debug(o, t, 0);
	}

	public static void debug(Object o, Throwable t, int userId){
		getLogger().debug(o, t);
		Service.getInstance().log(o.toString(), Service.getInstance().getDebugLevel(), userId);
		
	}
	
	public static void error(Object o){
		error(o, 0);
	}

	public static void error(Object o, int userId){
		getLogger().error(o);
		Service.getInstance().log(o.toString(), Service.getInstance().getErrorLogLevel(), userId);
		
	}
	
	public static void error(Object o, Throwable t){
		error(o, t, 0);
	}

	public static void error(Object o, Throwable t, int userId){
		getLogger().error(o, t);
		Service.getInstance().log(o.toString(), Service.getInstance().getErrorLogLevel(), userId);
		
	}
	public static void warn(Object o){
		warn(o, 0);
	}

	public static void warn(Object o, int userId){
		getLogger().warn(o);
		Service.getInstance().log(o.toString(), Service.getInstance().getWarnLogLevel(), userId);
		
	}
	public static void warn(Object o, Throwable t){
		warn(o, t, 0);
	}

	public static void warn(Object o, Throwable t, int userId){
		getLogger().warn(o, t);
		Service.getInstance().log(o.toString(), Service.getInstance().getWarnLogLevel(), userId);
		
	}
	public static void fatal(Object o){
		fatal(o, 0);
	}

	public static void fatal(Object o, int userId){
		getLogger().fatal(o);
		Service.getInstance().log(o.toString(), Service.getInstance().getFatalLogLevel(), userId);
		
	}
	public static void fatal(Object o, Throwable t){
		fatal(o, t, 0);
	}

	public static void fatal(Object o, Throwable t, int userId){
		getLogger().fatal(o, t);
		Service.getInstance().log(o.toString(), Service.getInstance().getFatalLogLevel(), userId);
		
	}
	public static void fatalNoConnection(Object o, Throwable t){
		getLogger().fatal(o, t);
		
	}
	
}
