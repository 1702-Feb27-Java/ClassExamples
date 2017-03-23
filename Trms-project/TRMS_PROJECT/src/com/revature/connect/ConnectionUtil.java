package com.revature.connect;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Singleton class to make a connection into the database. Will be used in Servlets
 * for database connection.
 * @author Nick
 *
 */
public class ConnectionUtil {
	
	private static ConnectionUtil cU = null;
	private Properties prop = null;
	private FileInputStream fis = null;
	private static String pUrl, pUname, pPword;
	
	private ConnectionUtil() {
		try {
			prop = new Properties();
			fis = new FileInputStream("C:/Users/Nick/Documents/workspace-sts-3.8.3.RELEASE/TRMS_PROJECT/DBProp.properties");
			prop.load(fis);
			pUrl = prop.getProperty("url");
			pUname = prop.getProperty("uname");
			pPword = prop.getProperty("pword");
			//System.out.println(pUrl);
			//System.out.println(pUname);
			//System.out.println(pPword);
			System.out.println("Connection established");
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Properties file not found");
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}
	
	/**
	 * Function to return the driver manager for the connection to the database
	 * @return Drive Manager connection
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		if ( cU == null) {
			cU = new ConnectionUtil();
		}
		
		return DriverManager.getConnection(pUrl, pUname, pPword);
	}
	
	//main function to test connection to the database
	/*
	public static void main(String []args) throws SQLException{
		
		ConnectionUtil con = new ConnectionUtil();
		
	}
	*/

}
