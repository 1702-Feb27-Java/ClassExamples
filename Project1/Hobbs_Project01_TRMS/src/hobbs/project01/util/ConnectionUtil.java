package hobbs.project01.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionUtil {

	private static Properties properties;
	private static String url, username, password;
	
	private ConnectionUtil() {
		
	}
	
	public static Connection getConnection() {
		try (InputStream fileInputStream = ConnectionUtil.class.getClassLoader().getResourceAsStream("trmsdb.properties")) {
			properties = new Properties();
			properties.load(fileInputStream);
			
			// retrieve the connection details
			Class.forName(properties.getProperty("driver"));
			url = properties.getProperty("url");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			
			//System.out.println(url + ", " + username + ", " + password); //DEBUGGING ONLY
			
			return DriverManager.getConnection(url, username, password);
		}
		catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
}
