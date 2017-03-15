package com.rev.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.rev.pojo.UserInformation;
import com.rev.util.ConnectionUtil;

public class DAOimpl implements DAO {

	private static ArrayList<UserInformation> userz = new ArrayList<>();
	

	@Override

	//public int users(String un, String rid) {
	public UserInformation getUserById(int id) {

		UserInformation all = new UserInformation();
		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);
	
			String sql = "Select * from users";
			PreparedStatement ps = connect.prepareStatement(sql);
			//ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			//if(rs.next()) System.out.println(rs.getInt(1));
			while (rs.next()) {
				//System.out.println(rs);

				all.setUn(rs.getString(2));
//				System.out.println("dbdb");
//				if(rs.next())
				all.setRid(rs.getString(4));
//				System.out.println("dbdb");

//				if(rs.next())
				all.setId(rs.getInt(1));      
				
				
				if(all.getRid().equals("1")){
					userz.add(all);
					
				}
				else if(all.getRid().equals("2")){
					userz.add(all);
				}	
				else if(all.getRid().equals("3")){
					userz.add(all);
				}
				
//				System.out.println("dbdb");
				//System.out.println(rs);
					}
			connect.commit();
			
			return all;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;

		//not sure if i need this
	}
	
	public void printall(){
		
		for(UserInformation u: userz){
			System.out.println(u.toString());
			
			
		}
		
		
		
		
	}

//	@Override
	public UserInformation getUserById2(int id) {
		return null;
	}


	@Override
	public int setBalance(UserInformation nusr, String username) {
		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);
			int id = nusr.getId();
			String sql = "UPDATE users SET role_id = " + " " +  "  WHERE users_id =" + id;
	
			Statement statement = connect.createStatement();
			int numrows = statement.executeUpdate(sql);
	
			connect.commit();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

//		UPDATE ACCOUNTS SET balance  = ' ' WHERE ACCOUNT_ID = '';

		return 0;
	}
	

	@Override
	public int updateUser(String b, String c, int d, int oldUserid) {
			int numRows;
		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);
			//int id = nusr.getId();
			String sql = "UPDATE users SET username = '" + b + "', pw = '" + c + "', role_id = " + d + " where users_id =" + oldUserid;
			Statement statement = connect.createStatement();
			int numrows = statement.executeUpdate(sql);
	
			connect.commit();
	
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(" update user dao method");
		}
		return 0;
	}

	@Override
	public ArrayList<UserInformation> getAllInfo() {
		try (Connection connect = ConnectionUtil.getConnection();) {
			connect.setAutoCommit(false);
			String sql = "select * from users";
			PreparedStatement ps = connect.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			UserInformation users;
			ArrayList<UserInformation> customer = new ArrayList();
			ArrayList<UserInformation> employee = new ArrayList();
			ArrayList<UserInformation> admin = new ArrayList();

			
			while (rs.next()) {
			
				users = new UserInformation();
				users.setId(rs.getInt(1));
				users.setRid(rs.getString("role_id"));
				users.setUn(rs.getString("username"));
				users.setPw(rs.getString("pw"));
		
			userz.add(users);
				
			}
			connect.commit();
		}
	
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error in get all info");
			return null;
		}
		return userz;}
	
	public static ArrayList<UserInformation> getArr(){
		return userz;
	}

	@Override
	public int users(String id, String rid) {
		return 0;
	}
	public void AdminPrintAll (){
		
		for(UserInformation u: userz){
			
			System.out.println(u.toString());
			
		}
		
		
	}

	@Override
	public int insertNewUser(String a, String b) {
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(true);
			String sql = "INSERT INTO users (users_id, username, pw, role_id) VALUES(  100, '" + a + "' , '" + b + "', 2)";  
			Statement statement = connect.createStatement();
			statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}




