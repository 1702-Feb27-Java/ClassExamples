package com.sqlbank.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil
{
	private static ConnectionUtil cu = null;
	private static Properties prop = null;
	private static FileInputStream fis = null;
	private static String pUrl, pUname, pPword;

	private ConnectionUtil ()
	{
		try
		{
			prop = new Properties();
			fis = new FileInputStream("DBProp.properties");
			prop.load(fis);
			pUrl = prop.getProperty("url");
			pUname = prop.getProperty("uname");
			pPword = prop.getProperty("pword");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			System.out.println("Propeties file is not found!");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	public static Connection getConnection() throws SQLException
	{
		if (cu == null)
			cu = new ConnectionUtil();
		return DriverManager.getConnection(pUrl,pUname,pPword);
	}
}
