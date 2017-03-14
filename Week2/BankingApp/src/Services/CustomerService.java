package Services;
import dao.CustomerDAOimpl;

public class CustomerService {
	
	public CustomerService(){};
	// customer sign up
	
	
	public void  signUp(String firstName, String lastName, String username, String password){
		// first check if username is present in database
		
		CustomerDAOimpl customer= new CustomerDAOimpl();
		customer.addCustomer(firstName, lastName, username, password);
		
	}

}
