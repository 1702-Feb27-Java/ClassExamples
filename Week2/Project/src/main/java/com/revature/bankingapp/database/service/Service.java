package com.revature.bankingapp.database.service;

import java.util.List;
import java.util.Map;

import com.revature.bankingapp.database.dao.Dao;
import com.revature.bankingapp.database.dao.IDao;
import com.revature.bankingapp.database.model.Account;
import com.revature.bankingapp.database.model.AccountStatus;
import com.revature.bankingapp.database.model.AccountType;
import com.revature.bankingapp.database.model.Role;
import com.revature.bankingapp.database.model.User;

public class Service implements IDao{

	private static Service instance;
	
	//key values for the maps
	public final static Integer  PENDING = 1;
	public final static Integer APPROVED = 2;
	public final static Integer REJECTED = 3;
	
	public final static Integer CUSTOMER = 1;
	public final static Integer EMPLOYEE = 2;
	public final static Integer ADMIN = 3;

	public final static Integer CHECKING = 1;
	public final static Integer SAVINGS = 2;
	
	
	private Service(){
		
	}
	
	public static Service getInstance(){
		if (instance == null){
			instance = new Service();
		}
		
		return instance;
	}
	
	public Role getAdminRole(){
		return this.getUserRoles().get(Service.ADMIN);
	}
	
	public Role getCustomerRole(){
		return this.getUserRoles().get(Service.CUSTOMER);
	}
	
	public Role getEmployeeRole(){
		return this.getUserRoles().get(Service.EMPLOYEE);
	}
	
	public AccountType getCheckingAccountType(){
		return this.getAccountTypes().get(Service.CHECKING);
	}
	
	public AccountType getSavingAccountType(){
		return this.getAccountTypes().get(Service.SAVINGS);
	}
	
	public AccountStatus getApprovedStatus(){
		return this.getAccountStatuses().get(Service.APPROVED);
	}
	
	public AccountStatus getRejectedStatus(){
		return this.getAccountStatuses().get(Service.REJECTED);
	}
	
	public AccountStatus getPendingStatus(){
		return this.getAccountStatuses().get(Service.PENDING);
	}
	
	
	public User saveAndReturnNewUser(User newUser){
		Dao.getInstance().saveNewUser(newUser);
		return Dao.getInstance().getUser(newUser.getUsername());
	}
	
	public boolean isAccountEditableByUser(User user, Account account){
		boolean b = false;
		if (user.getRole() == this.getUserRoles().get(3)){
			//is an admin role
			b = true;
		} 
		else if (user.getRole() == this.getUserRoles().get(1)){
			//is a customer role
			List<Account> accounts = this.getAccountFromUser(user);
			for (Account usersaccount: accounts){
				if (usersaccount.getAccountId().equals(account.getAccountId())){
					b = usersaccount.getStatus().equals(Service.getInstance().getApprovedStatus());
					break;
				}
			}
		}
		
		return b;
	}
	
	public boolean canUserApproveAccount(User user, Account account){
		boolean b;
		if (account.getStatus().equals(Service.getInstance().getAccountStatuses().get(Service.APPROVED))){
			b = false;
		} else {
			if (user.getRole().equals(Service.getInstance().getUserRoles().get(Service.EMPLOYEE)) 
					|| user.getRole().equals(Service.getInstance().getUserRoles().get(Service.ADMIN))){
				b = true;
			} else {
				b = false;
			}
		}
		
		return b;
	}
	
	
	
	
	@Override
	public void saveNewUser(User newUser) {
		// TODO Auto-generated method stub
		Dao.getInstance().saveNewUser(newUser);
	}

	@Override
	public void createNewAccount(User user, Account newAccount) {
		// TODO Auto-generated method stub
		Dao.getInstance().createNewAccount(user, newAccount);
		
	}

	@Override
	public boolean addUserToAccount(User user, Account account) {
		// TODO Auto-generated method stub
		return Dao.getInstance().addUserToAccount(user, account);
	}

	@Override
	public User getUser(Integer userId) {
		// TODO Auto-generated method stub
		return Dao.getInstance().getUser(userId);
	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return Dao.getInstance().getUser(username);
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return Dao.getInstance().deleteUser(user);
	}

	@Override
	public boolean deleteAccount(Account account) {
		// TODO Auto-generated method stub
		return Dao.getInstance().deleteAccount(account);
	}

	@Override
	public Account getAccount(Integer id) {
		// TODO Auto-generated method stub
		return Dao.getInstance().getAccount(id);
	}

	@Override
	public List<Account> getAccountFromUser(User user) {
		// TODO Auto-generated method stub
		return Dao.getInstance().getAccountFromUser(user);
	}

	@Override
	public Map<Integer, Role> getUserRoles() {
		// TODO Auto-generated method stub
		return Dao.getInstance().getUserRoles();
	}

	@Override
	public Map<Integer, AccountType> getAccountTypes() {
		// TODO Auto-generated method stub
		return Dao.getInstance().getAccountTypes();
	}

	@Override
	public Map<Integer, AccountStatus> getAccountStatuses() {
		// TODO Auto-generated method stub
		return Dao.getInstance().getAccountStatuses();
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return Dao.getInstance().updateUser(user);
	}

	@Override
	public boolean updateAccount(Account account) {
		// TODO Auto-generated method stub
		return Dao.getInstance().updateAccount(account);
	}

	public Account updateAndReturnAccount(Account account) {
		// TODO Auto-generated method stub
		this.updateAccount(account);
		return this.getAccount(account.getAccountId());
	}

	@Override
	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return Dao.getInstance().getAllAccounts();
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return Dao.getInstance().getAllUsers();
	}
}
