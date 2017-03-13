package com.revature.bankingapp.database.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLEngineResult.Status;

import com.revature.bankingapp.Logging;
import com.revature.bankingapp.database.connection.ConnectionUtil;
import com.revature.bankingapp.database.model.Account;
import com.revature.bankingapp.database.model.AccountStatus;
import com.revature.bankingapp.database.model.AccountType;
import com.revature.bankingapp.database.model.Role;
import com.revature.bankingapp.database.model.User;

public class Dao implements IDao {

	private static Dao instance;
	
	//get of these as a sort of a flyweight example
	private final Map<Integer, Role> roles;
	private final Map<Integer, AccountStatus> accountStatus;
	private final Map<Integer, AccountType> accountTypes;
	Connection connection;
	 
	
	private Dao(){
		

		Map<Integer, Role> r = new HashMap<>();
		Map<Integer, AccountStatus> a = new HashMap<>();
		Map<Integer, AccountType> t = new HashMap<>();
		try{
			// SQL strings to get the various constants from database
			connection = ConnectionUtil.getConnection();
			r = loadRoles(connection); 
			a = loadStatuses(connection);
			t = loadTypes(connection);
		} catch (SQLException e) {
			Logging.falalNoConnection("Unable to create connection.", e);
			e.printStackTrace();
			
		} 
		
		roles = r;
		accountStatus = a;
		accountTypes = t;
	
	}

	private Map<Integer, AccountType> loadTypes(Connection connection) {
		String getTypesSQL = 
				"SELECT type_id, type FROM account_type";
		Map<Integer, AccountType> types = new HashMap<>();
		try(Statement statement = connection.createStatement(); 
				ResultSet statusQuery = statement.executeQuery(getTypesSQL)){
			while(statusQuery.next() != false){
				Integer id = statusQuery.getInt(1);
				String typesName = statusQuery.getString(2);
				AccountType type = new AccountType(id, typesName);
				types.put(type.getTypeId(), type);
			}
		} catch (SQLException e) {
			Logging.fatal("Unable to load Statuses", e);
			e.printStackTrace();
		}
		return types;
	}

	private static Map<Integer, AccountStatus> loadStatuses(Connection connection){
		String getStatusesSQL =
				"SELECT status_id, status FROM STATUS";
		Map<Integer, AccountStatus> statuses = new HashMap<>();
		try(Statement statement = connection.createStatement(); 
				ResultSet statusQuery = statement.executeQuery(getStatusesSQL)){
			while(statusQuery.next() != false){
				Integer id = statusQuery.getInt(1);
				String statusName = statusQuery.getString(2);
				AccountStatus status = new AccountStatus(id, statusName);
				statuses.put(status.getStatusId(), status);
			}
		} catch (SQLException e) {
			Logging.fatal("Unable to load Statuses", e);
			e.printStackTrace();
		}
		return statuses;
	}


	private static Map<Integer, Role> loadRoles(Connection connection){
		String getRolesSQL = 
				"SELECT role_id, role FROM role";
		Map<Integer, Role> roles = new HashMap<>();
		try(Statement statement = connection.createStatement(); 
				ResultSet rolesQuery = statement.executeQuery(getRolesSQL)){
			while(rolesQuery.next() != false){
				Integer id = rolesQuery.getInt(1);
				String roleName = rolesQuery.getString(2);
				Role role = new Role(id, roleName);
				roles.put(role.getRoleId(), role);
			}
		} catch (SQLException e) {
			Logging.fatal("Unable to load Roles", e);
			e.printStackTrace();
		}
		return roles;
	}
	


	public static Dao getInstance(){
		if(instance == null){
			instance = new Dao();
		}
		
		return instance;
	}
	
	@Override
	public void saveNewUser(User newUser) {
		
		String sql = "INSERT INTO users "
				+ "(firstname, lastname, username, password, role_id) "
				+ "VALUES (?, ?, ?, ?, ?)";
		try(PreparedStatement addUserStatement = connection.prepareStatement(sql);){

			//adds the User to the database
			
			addUserStatement.setString(1, newUser.getFirstName());
			addUserStatement.setString(2,  newUser.getLastName());
			addUserStatement.setString(3,  newUser.getUsername());
			addUserStatement.setString(4, newUser.getPassword());
			addUserStatement.setInt(5, newUser.getRole().getRoleId());
			addUserStatement.executeQuery();
			addUserStatement.close();
		} catch (SQLException e) {
			newUser = null;
			e.printStackTrace();
		}
		

	}

	@Override
	public void createNewAccount(User user, Account newAccount) {
		String sql = "{CALL createAccountForUser(?, ?, ?)}";
		
		try(CallableStatement statement = connection.prepareCall(sql);){
			
			statement.setInt(1, user.getUserId());
			statement.setInt(2, newAccount.getAccountType().getTypeId());
			statement.setString(3, newAccount.getAccountName());
			statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean addUserToAccount(User user, Account account) {
		String sql = "INSERT INTO customer_accounts (user_id, account_id) values(?, ?)";
		try(PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setInt(1, user.getUserId());
			statement.setInt(2, account.getAccountId());
			return statement.executeUpdate() > 0 ? true: false;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public User getUser(Integer userId) {
		User returnedUser = null;
		String sql = "SELECT user_id, firstname, lastname, username, password, role_id "
				+ "FROM USERS "
				+ "WHERE USERS.user_id = ?";
		
		try(PreparedStatement statement = connection.prepareStatement(sql);){
			
			try(ResultSet result = statement.executeQuery()){
				if (result.next()){
					returnedUser = new User(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), this.getUserRoles().get(result.getInt(6)));
				} 
			} catch (SQLException e){
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return returnedUser;
	}

	@Override
	public User getUser(String username) {
		User returnedUser = null;
		// TODO Auto-generated method stub
		String sql = "SELECT u.user_id, u.firstname, u.lastname, u.username, u.password, u.role_id "
				+ "FROM users u "
				+ "WHERE u.username = ?";
		
		
		try(PreparedStatement getUserStatement = connection.prepareStatement(sql);){
			
			getUserStatement.setString(1, username);
			try(ResultSet result = getUserStatement.executeQuery()){
				if (result.next()){
					returnedUser = new User(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), this.getUserRoles().get(result.getInt(6)));
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnedUser;
	}

	@Override
	public Account getAccount(Integer id) {
		Account account = null;
		// TODO Auto-generated method stub
		String sql = "SELECT account_id, account_name, type_id, balance, status_id, resolver_id "
				+ "FROM accounts "
				+ "WHERE accounts.account_id = ?";
		
		try(PreparedStatement statement = connection.prepareStatement(sql)){

			
			statement.setInt(1, id);
			
			
			try(ResultSet result = statement.executeQuery()){
				if(result.next()){
					account = new Account(result.getInt(1), result.getString(2), this.getAccountTypes().get(result.getInt(3)), result.getDouble(4), this.getAccountStatuses().get(result.getInt(5)), 
							result.getInt(6) == 0 ? null : this.getUser(result.getInt(6)));
				}
			} catch(SQLException e){
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account;
	}

	@Override
	public List<Account> getAccountFromUser(User user) {
		List<Account> accounts = new LinkedList<>();
		String sql =
				"select a.account_id, a.account_name, a.type_id, a.balance, a.status_id, a.resolver_id "
				+ "from accounts a "
				+ "inner join customer_accounts ca "
				+ "on ca.account_id = a.account_id "
				+ "where ca.USER_ID = ?";
		try(PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setInt(1, user.getUserId());
			try(ResultSet result = statement.executeQuery()){
				while(result.next()){
					Account account = new Account(result.getInt(1), result.getString(2), this.getAccountTypes().get(result.getInt(3)), result.getDouble(4), 
							this.getAccountStatuses().get(result.getInt(5)), result.getInt(6) == 0 ? null : this.getUser(result.getInt(6)));
					accounts.add(account);
				}
			} catch (SQLException e){
				System.out.println(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public Map<Integer, Role> getUserRoles() {
		return roles;
	}

	@Override
	public Map<Integer, AccountType> getAccountTypes() {
		// TODO Auto-generated method stub
		return this.accountTypes;
	}

	@Override
	public Map<Integer, AccountStatus> getAccountStatuses() {
		// TODO Auto-generated method stub
		return this.accountStatus;
	}

	@Override
	public boolean deleteUser(User user) {
		
		String sql;
		boolean b = false;
		if (user.getUserId() != null){
			sql = "DELETE FROM USERS WHERE USERS.USER_ID = ?";
			try(PreparedStatement s = connection.prepareStatement(sql)){
				s.setInt(1, user.getUserId());
				b = s.executeUpdate() > 0 ? true: false;
			} catch(SQLException e){
				e.printStackTrace();
			}
		} else if (user.getUsername() != null) {
			sql = "DELETE FROM USERS WHERE USERS.USERNAME = ?";
			try(PreparedStatement s = connection.prepareStatement(sql)){
				s.setString(1, user.getUsername());
				b = s.executeUpdate() > 0 ? true : false;
			} catch (SQLException e){
				e.printStackTrace();
			}
		} else {
			b = false;
		}
		
		
		return b;
	}

	@Override
	public boolean deleteAccount(Account account) {
		String sql = "DELETE FROM accounts WHERE ACCOUNT_ID = ?";
		
		try(PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setInt(1, account.getAccountId());
			return statement.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateUser(User user) {
		String sql = "UPDATE USERS SET FIRSTNAME = ?, LASTNAME = ?, USERNAME = ?, PASSWORD = ?, ROLE_ID = ? WHERE USER_ID = ?";
		try(PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getUsername());
			statement.setString(4, user.getPassword());
			statement.setInt(5,user.getRole().getRoleId());
			statement.setInt(6, user.getUserId());
			return statement.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateAccount(Account account) {
		String sql = "UPDATE accounts SET account_name = ?, type_id = ?, balance=?, status_id = ?, resolver_id = ?  WHERE account_id=?";
		try(PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setString(1, account.getAccountName());
			statement.setInt(2, account.getAccountType().getTypeId());
			statement.setDouble(3, account.getBalance());
			statement.setInt(4, account.getStatus().getStatusId());
			if(account.getResolver() == null){
				statement.setNull(5,java.sql.Types.NUMERIC);
			} else {
				statement.setInt(5, account.getResolver().getUserId());
			}
			statement.setInt(6, account.getAccountId());
			return statement.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		String sql = "SELECT account_id, account_name, type_id, balance, status_id, resolver_id "
				+ "FROM accounts";
		List<Account> accounts = new LinkedList<>();
		try(Statement statement = connection.createStatement();){
			
			try(ResultSet result = statement.executeQuery(sql)){
				while(result.next()){
					Account account = new Account(result.getInt(1), result.getString(2), this.getAccountTypes().get(result.getInt(3)), result.getDouble(4), 
							this.getAccountStatuses().get(result.getInt(5)), result.getInt(6) == 0 ? null : this.getUser(result.getInt(6)));
					accounts.add(account);
				}
			} catch (SQLException e){
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public List<User> getAllUsers() {
		String sql = "SELECT u.user_id, u.firstname, u.lastname, u.username, u.password, u.role_id FROM users u";
		List<User> users = new LinkedList<User>();
		try(Statement statement = connection.createStatement()){
			
			
			try(ResultSet result = statement.executeQuery(sql)){
				while (result.next()){
					User returnedUser = new User(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), this.getUserRoles().get(result.getInt(6)));
					users.add(returnedUser);
				} 
			} catch (SQLException e){
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		return users;
	}






	
	

	
}