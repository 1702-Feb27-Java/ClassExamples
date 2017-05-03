import java.util.LinkedList;

public class Employee extends Person
{
	
	public void setCustomers(LinkedList<Customer> customers)
	{
		this.customers = customers;
	}


	private LinkedList<Customer> customers = new LinkedList<>();
	

	public LinkedList<Customer> getCustomers()
	{
		return customers;
	}
	
	
}
