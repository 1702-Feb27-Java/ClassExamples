package com.revature.bankingapp.menu;

import java.util.Scanner;


import com.revature.bankingapp.Logging;
import com.revature.bankingapp.Util;
import com.revature.bankingapp.model.Account;
import com.revature.bankingapp.model.Customer;
import com.revature.bankingapp.model.Employee;
import com.revature.bankingapp.model.User;

public class AccountMenu implements IMenu {



	private Account account;
	private boolean canEdit;
	private User user;
	private boolean canApprove;

	/**
	 * Creates the Account menu to view and control the Account menu,
	 * with only read only access to the account's data
	 * @param user User Object accessing this menu
	 * @param account Bank Account being accessed
	 */
	public AccountMenu(User user, Account account) {
		this(user, account, false);
	}
	
	/**
	 * Create the Account menu to view and control the Account menu with optional access to 
	 * withdraw, deposit, and approve account 
	 * @param user User Object accessing this menu
	 * @param account Bank Account being accessed
	 * @param canEdit permission to withdraw and deposit
	 * @param canApprove - permission to approve an account
	 */
	public AccountMenu(User user, Account account, boolean canEdit, boolean canApprove){
		this.user = user;
		this.account = account;
		this.canEdit = canEdit;
		this.canApprove = canApprove;
	}

	/**
	 * Creates the Account menu to view and control the Account menu with optional access to
	 * deposit, and withdraw.
	 * @param user User Object accessing this menu
	 * @param account Bank Account being accessed
	 * @param canEdit permission to withdraw and deposit
	 */
	public AccountMenu(User user, Account account, boolean canEdit) {
		this(user, account, canEdit, false);
	}


	@Override
	public IMenu openMenu(Scanner scan) {
		
		//Show account information
		System.out.println(String.format("Account Name: %s", account.getAccountName()));
		System.out.println(String.format("Account Type: %s", account.getAccountType()));
		System.out.println(String.format("Account Status: %s", account.isApproved() ? "true" : "false"));
		System.out.println(String.format("Balance: %.2f", account.getBalance()));
		
		// show if user is allowed able to withdraw and deposit
		if(canEdit){
			System.out.println("w: Withraw");
			System.out.println("d: Deposit");
		}
		
		// show if allowed to approve account 
		if(canApprove && account.isApproved() == false){
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
				if(user instanceof Customer ){
					menu = new CustomerMenu((Customer) user);
					isValidOption = true;
				}
				
				if(user instanceof Employee){
					menu = new EmployeeMenu((Employee) user);
					isValidOption = true;
				}
			}
			
			// approves account if allowed to approve account
			if(canApprove && !account.isApproved() && input.equals("a")){
				System.out.println("Account is now Approve");
				Logging.getLogger().info(String.format("%s ID: %d Name %s has approved account # %d",user.getUserType(),  user.getId(), user.getUsername(), account.getId()));
				account.setApproved(true);
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
								Logging.getLogger().warn(String.format("%s ID: %d Name: %s tried to withdraw more then they had on Account %d",user.getUserType(),  user.getId(), user.getUsername(), account.getId()));
								
								isValidOption = true;
							} else {
								System.out.println(String.format("You have withdrawed %.2f", amountToWithdraw));
								Logging.getLogger().info(String.format("%s ID: %d Name: %s withdrawed %.2f from Account %d",user.getUserType(), user.getId(), user.getUsername(), amountToWithdraw, account.getId()));
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
							Logging.getLogger().info(String.format("%s ID: %d Name: %s deposited %.2f into Account %n", user.getUserType(), user.getId(), user.getUsername(), amountToDeposit, account.getId()));
							account.setBalance(account.getBalance() + amountToDeposit);
							isValidOption = true;
						}			
					} while(amountToDeposit == null);
				}
			}
			
			
			if(!isValidOption){
				System.out.println("Invalid Option Selected");
			}
			
		}
		
		return menu;
		
	}

}
