package com.trms.connector;

import java.sql.Connection;
import java.sql.DriverManager;

public class TRMSConnector {
	
	static TRMSConnector connector = null;
	
	private TRMSConnector(){};
	
	private Connection createConnection(){
		Connection c = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "trmsuser", "trmspass");
		}catch(Exception e){
			System.out.println("Connection failed");
		}
		return c;
	}
	
	public static Connection getConnection(){
		if(connector == null){
			connector = new TRMSConnector();
		}
		return connector.createConnection();
	}

}
