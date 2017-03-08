package com.mory;

public class AppMenu {
	public static void main(String[] args) {
		// Add customer to customer Map
		Customer m1= new Customer("Mory", 1234,"checking",500);
		Transaction transaction1=Transaction.getTransaction();
		transaction1.addCustomerToDB(m1);
		transaction1.persistCustomerToDbfile(m1);
		transaction1.load();
		transaction1.displayList();
		/*
		Customer m2= new Customer("Aly", 1234,"checking",500);
		Transaction transaction2=Transaction.getTransaction();
		transaction2.addCustomerToDB(m2);
		transaction1.persistCustomerToDbfile(m2);
		System.out.print(Transaction.CUSTOMERINFOSMAP);
		Transaction.load();
		*/
	
	}

}
