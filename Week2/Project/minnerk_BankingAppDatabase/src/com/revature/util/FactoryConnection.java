package com.revature.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class FactoryConnection {
	private static FactoryConnection cf = null;
	private Properties prop = null;
	private FileInputStream fis = null;
	private static String pUrl, pUname, pPword;
	
	
	private FactoryConnection(){
		try{
			prop = new Properties();
			fis = new FileInputStream("bankDBprop.properties");
			prop.load(fis);
			pUrl = prop.getProperty("url");
			pUname = prop.getProperty("uname");
			pPword = prop.getProperty("pword");
			System.out.println("\nChecking User Credentials ? ? ?");
		}catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Properties file is not found!");
		}catch (IOException e){
			e.printStackTrace();
			System.out.println("Could not laod properties file!");
		}
	}
		
	private Connection createConnection(){
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(pUrl, pUname, pPword);
			}catch (SQLException e){
				e.printStackTrace();
				System.out.println("Could not establish database connection");
			}
			return conn;
		}
		
		public static Connection getConnection(){
			if(cf == null){
				cf = new FactoryConnection();
			}
			return cf.createConnection();
		}
}
