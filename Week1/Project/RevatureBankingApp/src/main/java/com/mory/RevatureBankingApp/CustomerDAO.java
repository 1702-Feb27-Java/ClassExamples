package com.mory.RevatureBankingApp;

import java.util.List;

interface CustomerDAO {
	
	List <Customer>getAllCustomers();
	Customer getCustomer(String name);
	void updateCustomer(Customer customer);
	void deleteCustomer(Customer customer);
	void addCustomer(Customer customer);
}
