package com.revature.bankingapp.database.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	private static Properties prop = null;

    //throws SQL Exception to calling method
    public static Connection getConnection() throws SQLException{
        prop = new Properties();
        try {
			prop.load(new FileInputStream("DBProp.properties"));
		     String url = prop.getProperty("url");
		        String username = prop.getProperty("username");
		        String password = prop.getProperty("password");
		        return DriverManager.getConnection(url, username, password);
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return null;
        
   }
    
}
