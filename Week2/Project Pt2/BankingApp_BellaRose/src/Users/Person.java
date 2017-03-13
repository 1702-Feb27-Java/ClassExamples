package Users;

public class Person
{

	private int userId;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private int type;
	
	
	public int getType()
	{
		return type;
	}
	public void setType(int type)
	{
		this.type = type;
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
	public void setLastName(String secondName)
	{
		this.lastName = secondName;
	}
	
	public int getUserId()
	{
		return userId;
	}
	
	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	
}
