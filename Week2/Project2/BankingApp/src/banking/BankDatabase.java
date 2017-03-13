package banking;

import java.util.ArrayList;

public class BankDatabase {
	private ArrayList<BankMember> data;
	
	public BankDatabase(){
		data = new ArrayList<BankMember>();
	}
	
	
	public void addMember(BankMember toAdd){
		data.add(toAdd);
	}
	
	/*
	 * checks if the user name is in the data base of members
	 * @param username- the username of the BankMember to check if the username is a valid one
	 * @return true if the username can be picked false if it can't
	 */
	public boolean checkUserNameAvailability(String userName){
		for(BankMember bm : data){
			if(bm.getUserName().equals(userName)) return false;		
		}
		return true;
	}
	
	/*
	 * returns the BankMember associated with the userName
	 * @param teh username to look for
	 * @return the BankMember with the userName or null if it doesn't exist
	 */
	public BankMember getAccount(String userName){
		for(BankMember bm : data){
			if(bm.getUserName().equals(userName)) return bm;		
		}
		return null;
	}
	
	/*
	 * return the database
	 */
	public ArrayList<BankMember> getArray(){
		return data;
	}
}
