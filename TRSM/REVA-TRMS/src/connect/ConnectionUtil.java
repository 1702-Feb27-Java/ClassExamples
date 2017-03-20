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

	public static Connection getConnection() throws SQLException{
		///new
		Properties prop = null;		
		//FileInputStream fi;
		try {
			//fi = new FileInputStream("DBProp.properties");
			
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            prop = new Properties();
            prop.load(classLoader.getResourceAsStream("DBProp.properties"));
            
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		
		
		userName = prop.getProperty("user");
		passName = prop.getProperty("pass");
		urlName = prop.getProperty("url");
		//System.out.println(userName + " " + passName + " " + urlName);
		////
		
//		String user = "chinook";
//		String pass = "p4ssw0rd";
//		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		//return DriverManager.getConnection(url, user, pass);
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

        try {
            Class.forName("oracle.jdbc.OracleDriver").newInstance();
        }catch(Exception e){
        	e.printStackTrace();
        }
		return DriverManager.getConnection(urlName, userName, passName);
	}

}
