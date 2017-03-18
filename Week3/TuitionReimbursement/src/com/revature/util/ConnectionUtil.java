package com.revature.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	
	private static Properties prop = null;
	private static FileInputStream fis = null;
	
	//throws SQL Exception to calling method
	public static Connection getConnection() throws SQLException{
		
		String pUrl = "";
		String pUsername = "";
		String pPassword = "";
		
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			Properties prop = new Properties();
			prop.load(classLoader.getResourceAsStream("DBProp.properties"));
			
			pUrl = prop.getProperty("url");
			pUsername = prop.getProperty("username");
			pPassword = prop.getProperty("password");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		System.out.println("teststststst");
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		System.out.println("test");
		try {
			Class.forName("oracle.jdbc.OracleDriver").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return DriverManager.getConnection(pUrl, pUsername, pPassword);
	}
	
}
