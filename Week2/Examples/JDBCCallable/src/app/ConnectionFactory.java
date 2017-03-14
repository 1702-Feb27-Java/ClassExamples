package app;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {
	
	private static Boolean build = true;
	private static ConnectionFactory cf = null;
	
	//Hide the constructor from the rest of the project with "private".
	//The compiler will not allow other programs to instantiate the class.
	private ConnectionFactory(){
		build = false;
	}
	
	//Provide callers with the ConnectionFactory object and synchronize to prevent two 
	//threads from creating objects simultaneously.
	public static synchronized ConnectionFactory getInstance(){
	
	if (build==true){
		cf = new ConnectionFactory();
	}
	return cf;	
	}

	//*********************************************************************//
	
	public Connection getConnection(){
		
		Connection conn = null;
		
		try{
			Properties prop = new Properties();
			prop.load(new FileReader("C:/Revature/training-staging-609b8ec522b1b3babcc701f5b3ce3279a528a7c4/training-staging-609b8ec522b1b3babcc701f5b3ce3279a528a7c4/Tech/Core Java/Brian/Examples/JDBCCallable/src/database.properties"));
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("usr"), prop.getProperty("pwd"));
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
			return conn;
	}


}
