package com.project1.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	 
    private static ConnectionUtil cf =  null;
    private static Properties prop = null;
    private static FileInputStream fis = null;
    private static String pUrl,pUname,pPword;
    public static Connection getConnection() throws SQLException
    {
       
    try{
         prop = new Properties();
        fis = new FileInputStream("blahblah.properties");
        prop.load(fis);
        pUrl = prop.getProperty("url");
        pUname = prop.getProperty("username");
        pPword = prop.getProperty("password");
       // System.out.println(pUrl +pUname+pPword);
    }
    catch(Exception e){
       
      //  e.printStackTrace();
    }
    
        return DriverManager.getConnection(pUrl,pUname,pPword);
   
       
       
    }
}