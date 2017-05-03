package com.rev.bankapp.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//creates a singleton connector
public class DBConnector {
	
	private Connection c = null;
	public static DBConnector dbc = null;
	
	private DBConnector(){
		
	}
	
	private Connection createNewConnect(){
		
		try{
			c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "BANKDB", "BANKPASS");
			
		}catch(Exception e){
			System.out.println("Connect failed");
		}
		
		return c;
		
	}

	
	public static Connection getConnection(){
		if(dbc == null){
			dbc = new DBConnector();
		}
		return dbc.createNewConnect();
	}
	
	
	
}
