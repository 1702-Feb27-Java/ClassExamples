package database;

import java.io.File;
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
        String pUrl = "";
        String pUsername="";
        String pPassword="";
    	prop = new Properties();
        /*try {
        	ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        	Properties prop = new Properties();
        	prop.load(classLoader.getResourceAsStream("DBProp.properties"));
        	//File file = new File("DBProp.properties");
			//prop.load(new FileInputStream(file));
		    pUrl = prop.getProperty("url");
		    pUsername = prop.getProperty("username");
		    pPassword = prop.getProperty("password");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        
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
        
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "trmsUser", "password");
        
   }
    
}
