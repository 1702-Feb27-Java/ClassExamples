package com.revature.banking2.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

	private static Properties properties;
	private static FileInputStream fileInputStream;
	private static String url, username, password;
	
	private ConnectionUtil() {
		
	}
	
	public static Connection getConnection() {
		try {
			properties = new Properties();
			fileInputStream = new FileInputStream("bankdb.properties");
			properties.load(fileInputStream);
			
			// retrieve the connection details
			url = properties.getProperty("url");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			
			return DriverManager.getConnection(url, username, password);
		}
		catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
