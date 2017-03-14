package com.revature.bankingapp;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.revature.bankingapp.database.dao.Dao;
import com.revature.bankingapp.database.model.Account;
import com.revature.bankingapp.database.model.AccountStatus;
import com.revature.bankingapp.database.model.AccountType;
import com.revature.bankingapp.database.model.Role;
import com.revature.bankingapp.database.model.User;
import com.revature.bankingapp.database.service.Service;

public class DaoTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetRoles(){
		Map<Integer, Role> roles = Dao.getInstance().getUserRoles();
		assertEquals(roles.size(), 3);
	}
	
	@Test
	public void testGetStatuses(){
		Map<Integer, AccountStatus> statuses = Dao.getInstance().getAccountStatuses();
		assertEquals(statuses.size(), 3);
	}
	
	@Test
	public void testGetTypes(){
		Map<Integer, AccountType> types = Dao.getInstance().getAccountTypes();
		assertEquals(types.size(), 2);
	}
	
	@Test
	public void testAddUser(){
		User user = new User("first", "last", "username12345", "password", Dao.getInstance().getUserRoles().get(1));
		Dao.getInstance().deleteUser(user);
		Dao.getInstance().saveNewUser(user);
		User resultingUser = Dao.getInstance().getUser(user.getUsername());
		assertEquals(user, resultingUser);
		
		System.out.println(resultingUser);
		
		Dao.getInstance().deleteUser(user);
	}
	
	@Test
	public void deleteUser(){
		User user = new User("first", "last", "username12345", "password", Dao.getInstance().getUserRoles().get(1));
		Dao.getInstance().saveNewUser(user);
		
		assertTrue(Dao.getInstance().deleteUser(user));
		
		assertNull(Dao.getInstance().getUser(user.getUsername()));
	}
	
	@Test
	public void failedDeleteUser(){
		User user = new User("first", "last", "doesnotexist", "password", Dao.getInstance().getUserRoles().get(1));
		assertFalse(Dao.getInstance().deleteUser(user));
		
	}
	
	@Test
	public void testAddAccount(){
		
		User user = new User("test", "new", "account", "password", Dao.getInstance().getUserRoles().get(1));
		Account account = new Account("new account", Dao.getInstance().getAccountTypes().get(1));
		Dao.getInstance().deleteUser(user);
		Dao.getInstance().saveNewUser(user);
		user = Dao.getInstance().getUser(user.getUsername());
		
		assertNotNull(user);
		
		Dao.getInstance().createNewAccount(user, account);
		assertFalse(Dao.getInstance().getAccountFromUser(user).isEmpty());
		account = Dao.getInstance().getAccountFromUser(user).get(0);
		
		
		Dao.getInstance().deleteAccount(account);
		Dao.getInstance().deleteUser(user);
	}
	
	@Test
	public void testAddUserToAccount(){
		User user = new User("test", "new", "accountTestOne", "password", Dao.getInstance().getUserRoles().get(1));
		User user2 = new User("test", "new", "accountTestTwo", "password", Dao.getInstance().getUserRoles().get(1));
		
		Account account = new Account("new account", Dao.getInstance().getAccountTypes().get(1));
		Dao.getInstance().deleteUser(user);
		Dao.getInstance().deleteUser(user2);
		//Dao.getInstance().deleteAccount(account);
		Dao.getInstance().saveNewUser(user);
		Dao.getInstance().saveNewUser(user2);
		
		user = Dao.getInstance().getUser(user.getUsername());
		user2 = Dao.getInstance().getUser(user2.getUsername());
		
		assertNotNull(user);
		assertNotNull(user2);
		
		Dao.getInstance().createNewAccount(user, account);
		
		account = Dao.getInstance().getAccountFromUser(user).get(0);
		
		assertFalse(Dao.getInstance().getAccountFromUser(user).isEmpty());
		assertEquals(true, Dao.getInstance().addUserToAccount(user2, account));
		assertEquals(false, Dao.getInstance().addUserToAccount(user2, account));
		
		Dao.getInstance().deleteAccount(account);
		Dao.getInstance().deleteUser(user);
		Dao.getInstance().deleteUser(user2);
	}
	
	@Test
	public void testUpdateUser(){
		User user = new User("test", "new", "testupdate", "password", Dao.getInstance().getUserRoles().get(1));
		
		Dao.getInstance().saveNewUser(user);
		user = Dao.getInstance().getUser(user.getUsername());
		
		user.setFirstName("first");
		user.setLastName("last");
		user.setPassword("p@ssw@rd");
		
		Dao.getInstance().updateUser(user);
		
		user = Dao.getInstance().getUser(user.getUsername());
		
		assertEquals("first", user.getFirstName());
		assertEquals("last", user.getLastName());
		assertEquals("p@ssw@rd", user.getPassword());
		
		Dao.getInstance().deleteUser(user);
	}
	
	@Test
	public void testUpdateAccount(){
		User user = new User("test", "new", "testupdatea", "password", Dao.getInstance().getUserRoles().get(1));
		Dao.getInstance().saveNewUser(user);
		user = Dao.getInstance().getUser(user.getUsername());
		
		Account account = new Account("newaccount", Service.getInstance().getSavingAccountType());
		Dao.getInstance().createNewAccount(user, account);
		account = Dao.getInstance().getAccountFromUser(user).get(0);
		
		account.setAccountName("accountname");
		account.setStatus(Service.getInstance().getApprovedStatus());
		account.setBalance(100.00);
		
		Dao.getInstance().updateAccount(account);
	
		account = Dao.getInstance().getAccount(account.getAccountId());
		
		assertEquals(account.getAccountName(), "accountname");
		assertEquals(account.getStatus(), Service.getInstance().getApprovedStatus());
		assertEquals(100.00, account.getBalance(), 0.009);
		
		Dao.getInstance().deleteUser(user);
		Dao.getInstance().deleteAccount(account);
	}
	
	
	

}
