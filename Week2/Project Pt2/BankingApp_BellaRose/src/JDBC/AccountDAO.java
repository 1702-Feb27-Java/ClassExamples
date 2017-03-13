package JDBC;

import java.util.List;
import Accounts.*;

public interface AccountDAO
{
	////// CREATE
	public void insertAccount(Account acc);

	///// READ
	public List<Account> getAllAccounts();
	public List<Account> getAccountsByType(int role);
	public List<Account> getAccountsByUserID(int id);

	////// UPDATE
	public void saveAccount(Account acc);

	////// DELETE
	public void deleteAccountByTypeAndUserId(int type, int id);

}
