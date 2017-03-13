package connect;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

	
	private static Properties prop = null;
	private FileInputStream fi = null;
	private static String userName, passName, urlName;
	
	////

	/**
	 * util method to connect to the database
	 * @return the connection
	 * @throws SQLException if connect fails
	 */
	public static Connection getConnection() throws SQLException{
		///new
		prop = new Properties();			
		FileInputStream fi;
		try {
			fi = new FileInputStream("DBProp.properties");
			prop.load(fi);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		userName = prop.getProperty("user");
		passName = prop.getProperty("pass");
		urlName = prop.getProperty("url");
		//System.out.println(userName + " " + passName + " " + urlName);
		////
		
//		String user = "bankdb";
//		String pass = "p4ssw0rd";
//		String url = "jdbc:oracle:thin:@localhost:1521:xe";
//		
//		
//		return DriverManager.getConnection(url, user, pass);
		
		return DriverManager.getConnection(urlName, userName, passName);
	}
}
