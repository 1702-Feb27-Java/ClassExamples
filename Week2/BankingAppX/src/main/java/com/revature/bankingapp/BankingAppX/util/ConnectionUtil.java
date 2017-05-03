package com.revature.bankingapp.BankingAppX.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil
{
	private static Properties prop = null;
	private static FileInputStream fis = null;
	private static String pUrl, pUname, pPword;
	public static Connection getConnection() throws SQLException
	{
		try
		{
			fis = new FileInputStream("DBProp.properties");
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prop = new Properties();
		try
		{
			prop.load(fis);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Test");
		pUrl = prop.getProperty("url");
		pUname = prop.getProperty("uname");
		pPword = prop.getProperty("pword");
		//System.out.println(pUrl + " " + pUname + " " + pPword);
		return DriverManager.getConnection(pUrl, pUname, pPword);
	}
}

