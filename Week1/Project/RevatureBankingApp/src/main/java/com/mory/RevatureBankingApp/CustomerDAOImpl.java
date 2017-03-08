package com.mory.RevatureBankingApp;

import java.util.List;

public  class CustomerDAOImpl implements CustomerDAO {
	/**
	 * customerList acts as a database of customers
	 */
	public static List<Customer> customersList;

	
	/**
	 * retrieve the list of customers
	 */
	public List<Customer> getAllCustomers() {
		
		return  customersList;
	}

	public Customer getCustomer(String name) {
		for(Customer customer:customersList){
			if(customer.getName().equals(name)){
				return customer;
			}
		}
		return null;
		
		
	}
	
	public void updateCustomer(Customer customer) {
		
		
		
	}
	
	/**
	 * delete a customer 
	 */
	public void deleteCustomer(Customer customer) {
		customersList.remove(customer);
		}

	public void addCustomer(Customer customer) {
		customersList.add(customer);
		
	}
	
		
	}
	
	
	

	
	
	
	

