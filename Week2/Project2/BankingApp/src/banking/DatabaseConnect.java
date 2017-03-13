package banking;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import connect.ConnectionUtil;


public class DatabaseConnect {
	
	
	/** 
	 * PREPAIRED QUESTION
	 * view employee and customer and customer accounts with applied accounts
	 */	
	public ArrayList<BankMember> getAllBankMembers() {
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			//System.out.println("into the try");
			String sql = "SELECT * FROM USERS";
			
			PreparedStatement ps = connect.prepareStatement(sql);			
			
			ResultSet rs = ps.executeQuery();
			ArrayList<BankMember> temp = new ArrayList<BankMember>();
			//System.out.println("into the try");
			while(rs.next()){
//				System.out.println("here IN RS");
//				System.out.println(rs.getString("PASS"));
				BankMember n = new BankMember(null, Type.CUSTOMER);
				n.setPassword(rs.getString("PASS"));
				n.setName(rs.getString("FULL_NAME"));
				n.setUserName(rs.getString("USERNAME"));
				//int x = rs.getInt("Role_id");
				temp.add(n);
			}
			//System.out.println(temp.size());
			connect.commit();
			connect.setAutoCommit(true);
			return temp;
					
		} catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * CALLABLE STATEMENT
	 * add user to data base
	 */	
	public int addUser(String name, String userName, String pass, Type type) {
		int temp = 0;
		
		if (type == Type.CUSTOMER) temp = 1;
		if (type == Type.EMPLOYEE) temp = 2;
		if (type == Type.ADMIN) temp = 3;
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			//?s can be set
			String sql = "CALL insertUser(?, ?, ?, ?)";
			//seting the call
			CallableStatement cs = connect.prepareCall(sql);
			cs.setString(1, name);
			cs.setString(2, userName);
			cs.setString(3, pass);
			cs.setInt(4, temp);
			cs.executeUpdate();
			
			//Statement statement = connect.createStatement();			
			connect.commit();
			connect.setAutoCommit(true);
			return 1;
					
		} catch(SQLException e){
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * gets if the userName exists 
	 * @param str
	 * @return
	 */
	public boolean isUNameAvalible(String str){
		ArrayList<BankMember> cards = new ArrayList<BankMember>();
		cards = this.getAllBankMembers();
		
		for(BankMember c : cards){
			if(str.equals(c.getUserName())){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * attemts to get the bankmember based on their userName
	 * @param userName
	 * @return
	 */
	public BankMember getBankMember(String userName) {
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "SELECT * FROM USERS WHERE USERNAME = ?";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setString(1,userName);
			
			ResultSet rs = ps.executeQuery();
			BankMember temp = new BankMember("null", Type.CUSTOMER);
			while(rs.next()){
				temp.setName(rs.getString("FULL_NAME"));
				temp.setUserName(rs.getString("USERNAME"));
				temp.setPassword(rs.getString("PASS"));
				
				int x = rs.getInt("ROLE_ID");
				
				Type type = Type.CUSTOMER;
				
				if(x == 1) type = Type.CUSTOMER;
				if(x == 2) type = Type.EMPLOYEE;
				if(x == 3) type = Type.ADMIN;
				temp.setType(type);
			}
			
			connect.commit();
			connect.setAutoCommit(true);
			if(temp.getType() == Type.CUSTOMER){
				temp.setChecking(this.getBalance(userName, 1));
				temp.setSavings(this.getBalance(userName, 2));
				temp.setCheckingStatus(this.getAccountStatus(userName, 1));
				temp.setSavingStatus(this.getAccountStatus(userName, 2));
			}
			//// put get savings/ account balance here;
			return temp;
					
		} catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public int getBalance(String userName, int typeAcc){
		
		
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "SELECT BALANCE FROM ACCOUNTS, CUSTOMERACCOUNTS, USERS WHERE USERS.USERNAME = ? AND USERS.USER_ID = CUSTOMERACCOUNTS.CUST_ID AND ACCOUNTS.ACCOUNT_ID = CUSTOMERACCOUNTS.ACCT_ID AND ACCOUNTS.ACCOUNT_TYPE_ID = ?";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setString(1,userName);
			ps.setInt(2, typeAcc);
			
			ResultSet rs = ps.executeQuery();
			int temp = 0;
			while(rs.next()){
				temp = rs.getInt("balance");				
			}
			
			connect.commit();
			connect.setAutoCommit(true);
			
			
			//// put get savings/ account balance here;
			return temp;
					
		} catch(SQLException e){
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public Status getAccountStatus(String userName, int x){

		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			String sql = "SELECT STATUS_ID FROM ACCOUNTS, USERS, CUSTOMERACCOUNTS WHERE ACCOUNTS.ACCOUNT_ID = CUSTOMERACCOUNTS.ACCT_ID AND CUSTOMERACCOUNTS.CUST_ID = USERS.USER_ID AND USERNAME = ? AND ACCOUNTS.ACCOUNT_TYPE_ID = ?";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setString(1,userName);
			ps.setInt(2, x);
			
			ResultSet rs = ps.executeQuery();
			Status temp = null;
			int xTemp = 0;
			while(rs.next()){
				xTemp = rs.getInt("STATUS_ID");	
				
				if (xTemp == 1) temp = Status.NONE;
				if (xTemp == 2) temp = Status.APPLIED;
				if (xTemp == 3) temp = Status.ACTIVE;
			}
			
			connect.commit();
			connect.setAutoCommit(true);
			
			
			//// put get savings/ account balance here;
			return temp;
					
		} catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public void setAccountStatus(String userName, int newStatus, int typeAcc){
		
			int temp = 0;
		
		
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			//?s can be set
			String sql = "CALL updateAccountStatus(?, ?, ?)";
			//seting the call
			CallableStatement cs = connect.prepareCall(sql);
			cs.setString(1, userName);
			cs.setInt(2, newStatus);
			cs.setInt(3, typeAcc);
			
			cs.executeUpdate();
			
			//Statement statement = connect.createStatement();			
			connect.commit();
			connect.setAutoCommit(true);
			
					
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		
	}
	
	public void setAccountBalance(String userName, int newBalance, int typeAcc){
		
		int temp = 0;
	
	
	try(Connection connect = ConnectionUtil.getConnection();){
		connect.setAutoCommit(false);
		
		//?s can be set
		String sql = "CALL updateBalance(?, ?, ?)";
		//seting the call
		CallableStatement cs = connect.prepareCall(sql);
		cs.setInt(1, typeAcc);
		cs.setString(2, userName);
		cs.setInt(3, newBalance);
		
		cs.executeUpdate();
		
		//Statement statement = connect.createStatement();			
		connect.commit();
		connect.setAutoCommit(true);
		
				
	} catch(SQLException e){
		e.printStackTrace();
	}
	
	
}
	
	
	public void printCustomers(){
		ArrayList<BankMember> bm = this.getAllBankMembers();
		
		for(BankMember b : bm){
			b = this.getBankMember(b.getUserName());
			if(b.getType() == Type.CUSTOMER){
				b = this.getBankMember(b.getUserName());
				System.out.println("UserName: " + b.getUserName() + " password: " + b.getPassword()
				+ " Name: " + b.getName() + " Checking Status: " + b.getCheckingStatus() 
				+ " Checking amount: "  + b.getChecking() + " Savings Status: " + b.getSavingStatus() + "Savings Amount: " + b.getSavings());
			}
		}
		
		
	}
	
	
	public void printAll(){
		ArrayList<BankMember> bm = this.getAllBankMembers();
		
		for(BankMember b : bm){
			b = this.getBankMember(b.getUserName());
			if(b.getType() == Type.CUSTOMER){
				
				System.out.println(b.getType() + " " + b.getUserName() + " " + b.getPassword()
				+ " " + b.getName() + " " + b.getCheckingStatus() 
				+ " "  + b.getChecking() + " " + b.getSavingStatus() + " " + b.getSavings());
			}
			else{
				System.out.println(b.getType() + " " + b.getUserName() + " " + b.getPassword() + " " + b.getName());
			}
		}
		
		
	}
	
	public void printApplied(){
		ArrayList<BankMember> bm = this.getAllBankMembers();
		
		for(BankMember b : bm){
			b = this.getBankMember(b.getUserName());
			if(b.getType() == Type.CUSTOMER && (b.getCheckingStatus() == Status.APPLIED || b.getSavingStatus() == Status.APPLIED)){
				
				System.out.println(b.getType() + " " + b.getUserName() + " " + b.getPassword()
				+ " " + b.getName() + " " + b.getCheckingStatus() 
				+ " "  + b.getChecking() + " " + b.getSavingStatus() + " " + b.getSavings());
			}
			
		}
		
	}
	
	
	
	/**
	 * CALLABLE STATEMENT
	 *updates the username of the given username
	 */	
	public int updateUName(String uName, String newUName) {
		
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			//?s can be set
			String sql = "CALL updateUName(?, ?)";
			//seting the call
			CallableStatement cs = connect.prepareCall(sql);
			cs.setString(1, uName);
			cs.setString(2, newUName);
			
			cs.executeUpdate();
			
			//Statement statement = connect.createStatement();			
			connect.commit();
			connect.setAutoCommit(true);
			return 1;
					
		} catch(SQLException e){
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
	
	
	

	/**
	 * CALLABLE STATEMENT
	 *updates the username of the given username
	 */	
	public int updatePass(String uName, String newPass) {
		
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			//?s can be set
			String sql = "CALL updatePass(?, ?)";
			//seting the call
			CallableStatement cs = connect.prepareCall(sql);
			cs.setString(1, uName);
			cs.setString(2, newPass);
			
			cs.executeUpdate();
			
			//Statement statement = connect.createStatement();			
			connect.commit();
			connect.setAutoCommit(true);
			return 1;
					
		} catch(SQLException e){
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
	/**
	 * CALLABLE STATEMENT
	 *updates the username of the given username
	 */	
	public int addLog(String message) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
			
			//?s can be set
			String sql = "CALL addLog(?, ?)";
			//seting the call
			CallableStatement cs = connect.prepareCall(sql);
			cs.setString(1, timeStamp);
			cs.setString(2, message);
			
			cs.executeUpdate();
			
			//Statement statement = connect.createStatement();			
			connect.commit();
			connect.setAutoCommit(true);
			return 1;
					
		} catch(SQLException e){
			e.printStackTrace();
		}
		return 0;
	}
		
	
	
	
	
	
	
	
	
	
	

}
