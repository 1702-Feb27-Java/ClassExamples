package com.revature.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class StudentPropertiesFile {

	Connection con;
	Statement stmt;
	Properties props = new Properties();

	public void studentOperations() {

		try {
			
			props.load(new FileInputStream ("datasource.properties"));
			
			Class.forName(props.getProperty("driver"));
			
			con = DriverManager.getConnection(props.getProperty("url"),props.getProperty("username"), props.getProperty("password"));
			
			
			
			stmt= con.createStatement();
			
			//this is to add multiple statements in a single transaction
			con.setAutoCommit(false);
			
			stmt.executeUpdate("insert into student values(6,'abc', 33)");
			stmt.executeUpdate("insert into student values(7,'def', 22)");
			stmt.executeUpdate("insert into student values(8,'ghi, 11)");

			con.commit();
			
			
			con.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

	
	public static void main(String[] args) {
		new StudentPropertiesFile().studentOperations();
		
	}
}
