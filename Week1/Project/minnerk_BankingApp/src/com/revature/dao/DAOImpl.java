package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.bankingapp.Customer;
import com.revature.util.FactoryConnection;

public class DAOImpl {

	public static int insertData(Customer c) {
		// TODO Auto-generated method stub
		try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
			String sql = "CALL insert_new_person(?, ?, ?, ?)";
			CallableStatement cs = connect.prepareCall(sql);
			cs.setString(1, c.getFirstName());
			cs.setString(2, c.getLastName());
			cs.setString(3, c.getUserName());
			cs.setString(4, c.getPassword());
			int numRows = cs.executeUpdate();
			connect.commit();
			return numRows;
		} catch (SQLException e) {
				e.printStackTrace();
		}
			return 0;
		}
	
	public static boolean verifyInfo(String s) {

		try (Connection connect = FactoryConnection.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "SELECT USERNAME FROM USERS WHERE USERNAME=?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, s);
			ResultSet rs = ps.executeQuery();
			Customer temp = new Customer();
			
			while (rs.next()){
				temp.setUserName(rs.getString(s));
				connect.commit();
				connect.close();
				return true;	
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}

