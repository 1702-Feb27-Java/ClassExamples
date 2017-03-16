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
			fis = new FileInputStream("DBProp.properties");
			prop = new Properties();
			prop.load(fis);
			pUrl = prop.getProperty("url");
			pUsername = prop.getProperty("username");
			pPassword = prop.getProperty("password");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		return DriverManager.getConnection(pUrl, pUsername, pPassword);
	}
	
}
