package Users;
import java.util.LinkedList;

public class Employee extends Person
{
	
	private LinkedList<Customer> customers = new LinkedList<>();
	
	public void setCustomers(LinkedList<Customer> customers)
	{
		this.customers = customers;
	}


	

	public LinkedList<Customer> getCustomers()
	{
		return customers;
	}
	
	
}
