package com.revature.sql;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	// for singleton implementation,
	// try catch block taking all the properties in
	// another getConnection method to utilize properties
	// getInstance method
	
	private static ConnectionFactory cf = new ConnectionFactory();
	private static Properties prop = null;
	private static FileInputStream fis = null;
	private static String pUrl, pUname, pPword = null;
	
	public static ConnectionFactory getInstance(){
		return cf;
	}
	
	
	// throw sql exception up so that statements can catch it
	public static Connection getConnection() throws SQLException {
		try {
			fis = new FileInputStream("WebContent/WEB-INF/lib/DBProp.properties");
			prop = new Properties();
			prop.load(fis);
			pUrl = prop.getProperty("url");
			pUname = prop.getProperty("uname");
			pPword = prop.getProperty("pword");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DriverManager.getConnection(pUrl, pUname, pPword);
	}
}
