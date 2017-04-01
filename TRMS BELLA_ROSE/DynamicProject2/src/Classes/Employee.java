package Classes;

public class Employee
{

	private int empId;
	private int roleId;
	private int deptId;
	private int dirSuperId;
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private String username;
	private String password;
	private float currentAllowance = 0;
	
	
	public Employee(int empId, int roleId, int deptId, int dirSuperId, String firstName, String lastName,
			String address, String email, String username, String password, float currentAllowance)
	{
		super();
		this.empId = empId;
		this.roleId = roleId;
		this.deptId = deptId;
		this.dirSuperId = dirSuperId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.username = username;
		this.password = password;
		this.currentAllowance = currentAllowance;
	}

	public Employee()
	{
		super();
	}

	public int getEmpId()
	{
		return empId;
	}
	
	public void setEmpId(int empId)
	{
		this.empId = empId;
	}
	
	public int getRoleId()
	{
		return roleId;
	}
	
	public void setRoleId(int roleId)
	{
		this.roleId = roleId;
	}
	
	public String getRole()
	{
		if (roleId == 1)
			return "Base Employee";
		else if (roleId == 2)
			return "Supervisor";
		else
			return "Department Head";
	}
	
	public int getDeptId()
	{
		return deptId;
	}
	
	public void setDeptId(int deptId)
	{
		this.deptId = deptId;
	}
	
	public String getDept()
	{
		if (deptId == 1)
			return "BenCo";
		else
			return "Marketing";
	}
	
	public int getDirSuperId()
	{
		return dirSuperId;
	}
	
	public void setDirSuperId(int dirSuperId)
	{
		this.dirSuperId = dirSuperId;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public float getCurrentAllowance()
	{
		return currentAllowance;
	}
	
	public void setCurrentAllowance(float currentAllowance)
	{
		this.currentAllowance = currentAllowance;
	}
	
	

}
