package com.mory;

public class AppMenu {
	public static void main(String[] args) {
		// Add customer to customer Map
		Customer m1= new Customer("Mory",123,54.4);
		
		System.out.println(m1.getAccount().getAccountNumber());
	
		/*CustomerTransactions transaction1=CustomerTransactions.getTransaction();
		transaction1.addToCustomerToDB(m1);
		*/
		/*ustomer m2= new Customer("Aly", 1234,"Saving",500);
		Transaction transaction2=Transaction.getTransaction();
		transaction2.addCustomerToDB(m2);
		transaction1.persistCustomerToDbfile(m2);
		
		Customer m3= new Customer("Aly", 1234,"checking",500);
		Transaction transaction3=Transaction.getTransaction();
		transaction2.addCustomerToDB(m3);
		transaction1.persistCustomerToDbfile(m3);
		*/
		System.out.println(CustomerTransactions.getMap());
	
		
	
	}

}
