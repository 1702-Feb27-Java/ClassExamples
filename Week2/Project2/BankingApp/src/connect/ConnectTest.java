package connect;

import banking.BankMember;
import banking.DatabaseConnect;
import banking.Type;

public class ConnectTest {

	public static void main(String[] args) {
		DatabaseConnect dao = new DatabaseConnect();
		
		
		//dao.addUser("Java", "yes", "temp pass", Type.ADMIN);
		
		//false
		//System.out.println(dao.isUNameAvalible("Bo$$"));
		
		//true
		//System.out.println(dao.isUNameAvalible("bob"));
		BankMember bm = dao.getBankMember("test acc");
		System.out.println(bm.getUserName() + " " + bm.getPassword() + " " + bm.getName() + " " + bm.getType() + " " 
		+ bm.getChecking() +  " " + bm.getSavings() + " " + bm.getCheckingStatus() + " " + bm.getSavingStatus());
//		
		
		
		
		
		
//		for (BankMember b : dao.getAllBankMembers()){
//			System.out.println(b.toString());
//		}

	}

}
