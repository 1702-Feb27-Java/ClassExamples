package com.revature.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// this connection factory is based on the example Ryan did in class

public class ConnectionClass {

	private static ConnectionClass c = null;
	private Properties prop = null;
	private FileInputStream f = null;
	private static String url, uname, pw;

	private ConnectionClass() {

		try {
			prop = new Properties();
			f = new FileInputStream("DBProperties.properties");
			prop.load(f);

			url = prop.getProperty("url");
			uname = prop.getProperty("uname");
			pw = prop.getProperty("pword");

			//System.out.println(url + " " + uname + " " + pw);

		} catch (IOException e) {
			e.getStackTrace();
		}

	}
	
	private Connection createConnection(){
		Connection conn = null; //Create a connection reference.
		try{
			conn = DriverManager.getConnection(url, uname, pw); //Assign a connection
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Could not establish database connection.");
		}
		return conn;
	}
	
	public static Connection getConnection() {
		if(c == null){
			c = new ConnectionClass();
		}
		return c.createConnection();
	}
}