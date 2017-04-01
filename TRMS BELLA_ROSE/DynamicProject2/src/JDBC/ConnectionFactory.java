package JDBC;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory
{
	
	private static Boolean isNull = true;
	private static ConnectionFactory cf = null;
	
	private ConnectionFactory()
	{
		isNull = false;
	}
	
	static
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public static synchronized ConnectionFactory getInstance()
	{
		
		if (isNull == true)
		{
			cf = new ConnectionFactory();
		}
		
		return cf;
	}
	
	public static Connection getConnection()
	{
		Connection conn = null;
		
		try
		{
//			Properties prop = new Properties();
//			prop.load(new FileReader("DBProp.properties"));
			//Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "TRMSDB", "admin");
		} catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		return conn;
	}
	
}