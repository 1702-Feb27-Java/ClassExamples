package com.revature.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil
{
	private static FileInputStream fis = null;
	private static Properties prop = null;
	private static String pUrl, pUname, pPword;
	public static Connection getConnection() throws SQLException
	{
		try
		{
			prop = new Properties();
			
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            prop.load(classLoader.getResourceAsStream("DBProp.properties"));
//            fis = new FileInputStream("C:/Users/Xavier/Documents/workspace-sts-3.8.3.RELEASE/TRMS/DBProp.properties");
//            prop.load(fis);
            pUrl = prop.getProperty("url");
    		pUname = prop.getProperty("uname");
    		pPword = prop.getProperty("pword");
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		try
		{
			Class.forName("oracle.jdbc.OracleDriver").newInstance();
		}
		catch(InstantiationException e)
		{
			e.printStackTrace();
		}
		catch(IllegalAccessException e ) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(pUrl, pUname, pPword);
	}
}
