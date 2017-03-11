package com.revature.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/**
 * 
 * @author Ryan
 *
 *The purpose of this class is to offer a singleton class that serves to
 *dish out unique instances of a connection to the database.
 *Connection details are grabbed from a properties file.
 *
 */
public class ConnectionFactory {
	//Instance variables
	private static ConnectionFactory cf = null; //Single instance is null until needed
	private Properties prop = null;				//Properties object for connection details
	private FileInputStream fis = null;			//Stream for reading the input
	private static String pUrl, pUname, pPword;	//Strings to store connection details

	/**
	 * This constructor is private to enforce singleton design.
	 * 
	 */
	private ConnectionFactory() {
		try {
			prop = new Properties();						//Create instance of properties.
			fis = new FileInputStream("DBProp.properties");	//Read in file with stream.
			prop.load(fis);									//Load contents of file into properties object.
			pUrl = prop.getProperty("url");					//Grab properties using their key.
			pUname = prop.getProperty("uname");
			pPword = prop.getProperty("pword");

		} catch (FileNotFoundException e) { //Be a good programmer and catch exceptions.
			e.printStackTrace();
			System.out.println("Properties file is not found!");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not load properties file!");
		}
	}
	/**
	 * This method will return a unique connection instance every time it's called.
	 * 
	 */
	private Connection createConnection(){
		Connection conn = null; //Create a connection reference.
		try{
			conn = DriverManager.getConnection(pUrl, pUname, pPword); //Assign a connection
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Could not establish database connection.");
		}
		return conn;
	}

	/**
	 * 
	 *When the below method is called, it will check to see if our static instance
	 *exists yet. If it does not, the instance will be created.
	 *If it does exist, then we will utilize the same instance.
	 *
	 *Note, upon creating the instance, the constructor will take in the properties details as
	 *described in the constructor.
	 */
	public static Connection getConnection() {
		if(cf == null){
			cf = new ConnectionFactory();
		}
		return cf.createConnection();
	}
}
