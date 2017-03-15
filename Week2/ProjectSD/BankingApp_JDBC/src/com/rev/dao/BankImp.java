package com.rev.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.rev.pojo.BAccount;
import com.rev.pojo.Users;
import com.rev.util.ConnectionUtil;

public class BankImp implements Bank{

	@Override
	public String getUser(String uName) {
		// TODO Auto-generated method stub
		
		try (Connection con= ConnectionUtil.getConnection();){
			con.setAutoCommit(false);
			
			String sql = "Select first_name from Users where username=?";
			PreparedStatement pStat = con.prepareCall(sql);
			pStat.setString(1,uName);
			
			ResultSet rs = pStat.executeQuery();
			
			con.commit();
			
			if(rs.next())
				return uName;
			else
				return "";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean checkLogin(String uName, String pWd) {
		// TODO Auto-generated method stub
		try (Connection con= ConnectionUtil.getConnection();){
			con.setAutoCommit(false);
			
			String sql = "Select * from Users where username=? AND PQ=?";
			PreparedStatement pStat = con.prepareCall(sql);
			pStat.setString(1,uName);
			pStat.setString(2,pWd);
			
			ResultSet bRet = pStat.executeQuery();
			con.commit();
			
			if(bRet.next())
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean regUser(String fName, String lName, String uName, String sPwd, int iRole) {
		// TODO Auto-generated method stub
		try (Connection con= ConnectionUtil.getConnection();){
			con.setAutoCommit(false);
			
			//String sql = "insert into users values('',?,?,?,?,?)";
			//PreparedStatement pStat = con.prepareStatement(sql);
			String sql = "{call regUsers (?,?,?,?,?)}";
			CallableStatement pStat = con.prepareCall(sql);
			pStat.setString	(1,fName);
			pStat.setString	(2,lName);
			pStat.setString	(3,uName);
			pStat.setString	(4,sPwd);
			pStat.setInt	(5,iRole);
			
			ResultSet bRet = pStat.executeQuery();
			
			con.commit();
			
			if(bRet != null)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Users getUserDetails(String uName) {
		// TODO Auto-generated method stub
		try (Connection con= ConnectionUtil.getConnection();){
			con.setAutoCommit(false);
			
			String sql = "select user_id,role_id from users where username=?";
			PreparedStatement pStat = con.prepareCall(sql);
			pStat.setString	(1,uName);
			
			ResultSet bRet = pStat.executeQuery();
		
			Users uUser = new Users();
			while(bRet.next()){
				uUser.setUserID(bRet.getInt(1));
				uUser.setRoleID(bRet.getInt(2));
				uUser.setUserName(uName);
			}
			
			con.commit();
			return uUser;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//Customer
	@Override
	public boolean createAcc(String uName, int typeAcc, double dAmount) {
		// TODO Auto-generated method stub
		try (Connection con= ConnectionUtil.getConnection();){
			con.setAutoCommit(false);
			int ret=0;
			String sql = "call createAccount(?,?,?)";
			CallableStatement pStat = con.prepareCall(sql);
			pStat.setString	(1,uName);
			pStat.setInt	(2,typeAcc);
			pStat.setDouble	(3,dAmount);
			
			pStat.executeUpdate();
			con.commit();
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public boolean updateAcc(String uName, double dAmount) {
		// TODO Auto-generated method stub
		try (Connection con= ConnectionUtil.getConnection();){
			con.setAutoCommit(false);
			
			String sql = "{call updateAcct(?,?)}";
			CallableStatement pStat = con.prepareCall(sql);
			pStat.setString	(1,uName);
			pStat.setDouble	(2,dAmount);
			
			ResultSet bRet = pStat.executeQuery();
			con.commit();
			
			if(bRet != null)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteAcc(String uName) {
		// TODO Auto-generated method stub
		try (Connection con= ConnectionUtil.getConnection();){
			con.setAutoCommit(false);
			
			String sql = "{call deleteAcc(?)}";
			CallableStatement pStat = con.prepareCall(sql);
			pStat.setString	(1,uName);
			
			ResultSet bRet = pStat.executeQuery();
			con.commit();
			
			if(bRet != null)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public BAccount getAccount(String iUserName) {
		// TODO Auto-generated method stub
		
		try (Connection con= ConnectionUtil.getConnection();){
			con.setAutoCommit(false);
			
			String sql = "select * from bAccounts where user_id=(select user_id from users where username=?)";
			PreparedStatement pStat = con.prepareCall(sql);
			pStat.setString	(1,iUserName);
			
			BAccount bAcc = new BAccount();
			ResultSet bRet = pStat.executeQuery();
			
			while(bRet.next()){
				bAcc.setAccNo(bRet.getInt(1));
				bAcc.setAccType(bRet.getInt(2));
				bAcc.setAccBal(bRet.getDouble(3));
				bAcc.setStatusID(bRet.getInt(4));
				bAcc.setUserID(bRet.getInt(5));
				bAcc.setTask(bRet.getString(6));
			}
			
			con.commit();
			return bAcc;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//Employee
	@Override
	public ArrayList<BAccount> getAccounts(int statusId) {
		// TODO Auto-generated method stub
		try (Connection con= ConnectionUtil.getConnection();){
			con.setAutoCommit(false);
			
			String sql = "select * from bAccounts where status_id=?";
			PreparedStatement pStat = con.prepareCall(sql);
			pStat.setInt(1,statusId);
			
			ResultSet bRet = pStat.executeQuery();
			
			int iC=0;
			ArrayList<BAccount> aBA = new ArrayList();
			while(bRet.next()){
				BAccount bAcc = new BAccount();
				bAcc.setAccNo(bRet.getInt(1));
				bAcc.setAccType(bRet.getInt(2));
				bAcc.setAccBal(bRet.getDouble(3));
				bAcc.setUserID(bRet.getInt(5));
				bAcc.setTask(bRet.getString(6));

				aBA.add(bAcc);
			}
			
			con.commit();
			return aBA;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean createAccA(int iAccNo, double dAmt, int statusID,String userName) {
		// TODO Auto-generated method stub
		try (Connection con= ConnectionUtil.getConnection();){
			con.setAutoCommit(false);
			
			String sql = "{call appAcc(?,?,?,?)}";
			CallableStatement pStat = con.prepareCall(sql);
			pStat.setInt	(1,iAccNo);
			pStat.setDouble	(2,dAmt);
			pStat.setInt	(3,statusID);
			pStat.setString (4,userName);
			
			ResultSet bRet = pStat.executeQuery();
			
			con.commit();
			
			if(bRet != null)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateApp(int iAccNo, int statusID,double dAmt,String userName) {
		// TODO Auto-generated method stub
		try (Connection con= ConnectionUtil.getConnection();){
			con.setAutoCommit(false);
			
			String sql = "{call updateApp(?,?,?,?)}";
			CallableStatement pStat = con.prepareCall(sql);
			pStat.setInt	(1,iAccNo);
			pStat.setInt	(2,statusID);
			pStat.setDouble	(3,dAmt);
			pStat.setString	(4,userName);
			
			ResultSet bRet = pStat.executeQuery();
			con.commit();
			
			if(bRet !=null)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean deleteAccA(int iAccNo, int statusID,double dAmt,String userName) {
		// TODO Auto-generated method stub
		try (Connection con= ConnectionUtil.getConnection();){
			con.setAutoCommit(false);
			
			String sql = "{call appDelete(?,?,?,?)}";
			CallableStatement pStat = con.prepareCall(sql);
			pStat.setInt	(1,iAccNo);
			pStat.setInt	(2,statusID);
			pStat.setDouble	(3,dAmt);
			pStat.setString	(4,userName);
			
			ResultSet bRet = pStat.executeQuery();
			con.commit();
			
			if(bRet !=null)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
