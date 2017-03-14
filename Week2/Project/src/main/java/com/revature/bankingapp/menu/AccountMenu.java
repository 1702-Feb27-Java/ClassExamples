package com.revature.bankingapp.menu;

import java.util.Scanner;


import com.revature.bankingapp.Logging;
import com.revature.bankingapp.Util;
import com.revature.bankingapp.database.model.Account;
import com.revature.bankingapp.database.model.User;
import com.revature.bankingapp.database.service.Service;

public class AccountMenu implements IMenu {



	private final Account account;
	private final boolean canEdit;
	private final User user;
	private final boolean canApprove;

	
	/**
	 * Creates the Account menu to view and control the Account menu,
	 * with only read only access to the account's data
	 * @param user User Object accessing this menu
	 * @param account Bank Account being accessed
	 */
//	public AccountMenu(User user, Account account) {
//		this(user, account, false);
//	}
	
	/**
	 * Create the Account menu to view and control the Account menu with optional access to 
	 * withdraw, deposit, and approve account 
	 * @param user User Object accessing this menu
	 * @param account Bank Account being accessed
	 * @param canEdit permission to withdraw and deposit
	 * @param canApprove - permission to approve an account
	 */
//	public AccountMenu(User user, Account account, boolean canEdit, boolean canApprove){
//		this.user = user;
//		this.account = account;
//		this.canEdit = canEdit;
//		this.canApprove = canApprove;
//	}

	/**
	 * Creates the Account menu to view and control the Account menu with optional access to
	 * deposit, and withdraw.
	 * @param user User Object accessing this menu
	 * @param account Bank Account being accessed
	 * @param canEdit permission to withdraw and deposit
	 */
//	public AccountMenu(User user, Account account, boolean canEdit) {
//		this(user, account, canEdit, false);
//	}
//
	
	public AccountMenu(User user, Account account){
		this.account = account;
		this.user = user;
		this.canApprove = Service.getInstance().canUserApproveAccount(user, account);
		this.canEdit = Service.getInstance().isAccountEditableByUser(user, account);
	}

	@Override
	public IMenu openMenu(Scanner scan) {
		
		//Show account information
		System.out.println(String.format("Account Name: %s", account.getAccountName()));
		System.out.println(String.format("Account Type: %s", account.getAccountType()));
		System.out.println(String.format("Account Status: %s", account.getStatus().getStatus()));
		System.out.println(String.format("Balance: %.2f", account.getBalance()));
		
		// show if user is allowed able to withdraw and deposit
		if(canEdit){
			System.out.println("w: Withraw");
			System.out.println("d: Deposit");
		}
		
		// show if allowed to approve account 
		if(canApprove){
			System.out.println("a: approve");
		}
		
		System.out.println("q: Exit menu");
		boolean isValidOption = false;
		IMenu menu = null;
		while(menu == null){
			System.out.print("Please enter your option.");
			String input = scan.nextLine();
			
			//goes back to the main menu screen for the user
			if(input.equals("q")){
				if(user.getRole().equals(Service.getInstance().getUserRoles().get(Service.CUSTOMER))){
					menu = new CustomerMenu(user);
					isValidOption = true;
					
				}
				
				if(user.getRole().equals(Service.getInstance().getUserRoles().get(Service.ADMIN))
						|| (user.getRole().equals(Service.getInstance().getUserRoles().get(Service.EMPLOYEE)))){
					menu = new EmployeeMenu(user);
					isValidOption = true;
					
				}
			}
			
			// approves account if allowed to approve account
			if(canApprove && input.equals("a")){
				System.out.println("Account is now Approve");
				Logging.info(String.format("%s ID: %d Name %s has approved account # %d",user.getRole().getRole(),  user.getUserId(), user.getUsername(), account.getAccountId()), user.getUserId());
				account.setStatus(Service.getInstance().getAccountStatuses().get(Service.APPROVED));;
				isValidOption = true;
			}
			
			
			//Requires access to edit this before it can withdraw and deposit this.
			if(canEdit){
				//user wants to withdraw
				if(input.equals("w")){
					Double amountToWithdraw = null;
					do{
						System.out.print("Please enter how much you want to withdraw: ");
						amountToWithdraw = Util.getDoubleFromScanner(scan);
						if(amountToWithdraw == null || amountToWithdraw < 0){
							System.out.println("Not a valid number.");
						} else {
							
							//limits to only the first 2 decimal points can be used
							amountToWithdraw = Double.valueOf(String.format("%.2f", amountToWithdraw));
							
							// updates the account's balance, if not overdrawing
							if(amountToWithdraw > account.getBalance()){
								System.out.println("You do not have enough to withdraw that amount.");
								Logging.warn(String.format("%s ID: %d Name: %s tried to withdraw more then they had on Account %d",user.getRole().getRole(),  user.getUserId(), user.getUsername(), account.getAccountId()), user.getUserId());
								
								isValidOption = true;
							} else {
								System.out.println(String.format("You have withdrawed %.2f", amountToWithdraw));
								Logging.info(String.format("%s ID: %d Name: %s withdrawed %.2f from Account %d",user.getRole().getRole(), user.getUserId(), user.getUsername(), amountToWithdraw, account.getAccountId()), user.getUserId());
								account.setBalance(account.getBalance() - amountToWithdraw);
								isValidOption = true;
							}
						}			
					} while(amountToWithdraw == null);
				}
				
				//user wants to deposit
				if(input.equals("d")){
					Double amountToDeposit = null;
					do{
						System.out.print("Please enter how much you want to withdraw: ");
						amountToDeposit = Util.getDoubleFromScanner(scan);
						if(amountToDeposit == null || amountToDeposit < 0){
							System.out.println("Not a valid number.");
						} else {
							//limits to only the first 2 decimal points can be used
							amountToDeposit = Double.valueOf(String.format("%.2f", amountToDeposit));
							
							// updates the account's balance
							System.out.println(String.format("You have deposited %.2f", amountToDeposit));
							Logging.info(String.format("%s ID: %d Name: %s deposited %.2f into Account %n", user.getRole().getRole(), user.getUserId(), user.getUsername(), amountToDeposit, account.getAccountId()), user.getUserId());
							account.setBalance(account.getBalance() + amountToDeposit);
							isValidOption = true;
						}			
					} while(amountToDeposit == null);
				}
			}
			
			
			if(!isValidOption){
				System.out.println("Invalid Option Selected");
			} else {
				//updates account and menu
				Account updatedAccount = Service.getInstance().updateAndReturnAccount(account);
				if (menu == null)
					//updates menu for account depsoit and withdraw
					menu = new AccountMenu(user, updatedAccount);
			}
			
		}
		
		return menu;
		
	}

}
