package com.rev.util;

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
	private static String url,name,pwd;
	
	public static Connection getConnection() throws SQLException{	
		try {
			prop = new Properties();
			fis = new FileInputStream("DBProp.properties");
			prop.load(fis);
			url = prop.getProperty("url");
			name=prop.getProperty("uname");
			pwd=prop.getProperty("pword");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return DriverManager.getConnection(url,name,pwd);
	}
}
