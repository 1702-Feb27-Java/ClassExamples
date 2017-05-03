package com.revature.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class FactoryConnection {
	private static FactoryConnection cf = null;
	private Properties prop = null;
	private FileInputStream fis = null;
	private static String pUrl, pUname, pPword;
	
	private FactoryConnection(){
		try{
			//prop = new Properties();
			//fis = new FileInputStream("trms.properties");
			//prop.load(fis);
			pUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			pUname = "minncomm";
			pPword = "m1nnc0mm";
			System.out.println("\nChecking User Credentials ? ? ?");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Properties file is not found!!");
		}
	}
		
	private Connection createConnection(){
		Connection conn = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(pUrl, pUname, pPword);
			}catch (SQLException e){
				e.printStackTrace();
				System.out.println("Could not establish database connection");
			}catch (Exception e){
				e.printStackTrace();
			}
			return conn;
		}
		
		public static Connection getConnection(){
			if(cf == null){
				cf = new FactoryConnection();
			}
			return cf.createConnection();
		}
	}
