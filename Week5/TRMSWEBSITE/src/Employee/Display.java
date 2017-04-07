package Employee;

public class Display {
	
	
	public static String displayRole(int role_id)
	{
		switch(role_id)
		{
		
		
		case 1:
			return "Employee";
			
		case 2:
			return "Direct Supervisor";
		case 3:
			return "Department Head";
			
	
		}
		return "null";
	
	}
	
	public static String displayDepartment(int role_id)
	{
		switch(role_id)
		{
		
		
		case 1:
			
			return "Information Technology";
		case 2:
			return "Human Resources";
		case 3:
			return "Sales";
		case 4:
			return "Accounting";
		case 5:
			return "Quality Control";
		case 6:
			return "Benco" ;
		}
		return "";
	}
	
	public static String displayStatus(int role_id)
	{
		switch(role_id)
		{
		
		
		case 1:
			
			return "New";
		case 2:
			return "Direct Supervisior";
		case 3:
			return "Department Head";
		case 4:
			return "Benco";
		case 5:
			return "Finished";
		case 6:
			return "Request More Info";
		case 7:
			return "Rejected";
		case 8:
			return "Benco Pending";
						
		}
		return "";
	}


}
