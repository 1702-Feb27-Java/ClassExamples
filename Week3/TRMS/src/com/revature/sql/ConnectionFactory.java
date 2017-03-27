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
	// request.getContextPath()
	public static Connection getConnection() throws SQLException {
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			Properties prop = new Properties();
			prop.load(classLoader.getResourceAsStream("DBProp.properties"));
			pUrl = prop.getProperty("url");
			pUname = prop.getProperty("uname");
			pPword = prop.getProperty("pword");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        try {
            Class.forName("oracle.jdbc.OracleDriver").newInstance();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		return DriverManager.getConnection(pUrl, pUname, pPword);
	}
}
