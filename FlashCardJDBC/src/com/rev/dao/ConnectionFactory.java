package com.rev.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	
	private Properties prop = null;
	private FileInputStream f = null;
	private String url, uname, pw;
	
	private ConnectionFactory(){
		
		try{
		prop = new Properties();
		f = new FileInputStream("DBProperties.properties");
		prop.load(f);
		
		url = prop.getProperty("url");
		uname = prop.getProperty("uname");
		pw = prop.getProperty("pword");
		
		System.out.println(url +  " " + uname  + " " + pw);
		
		} catch(IOException e){
			e.getStackTrace();
		}
		
	}
	
	private Connection createConnection(){
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, uname, pw);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
//	public static Connection getConnection(){
//		if(cf = null){
//			cf = new COnnectionFactory();
//		}
//		return cf.createConnection();
//	}

}
