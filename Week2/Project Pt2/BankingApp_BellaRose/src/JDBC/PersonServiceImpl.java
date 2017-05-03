package JDBC;

import java.util.List;
import Users.Person;



public class PersonServiceImpl implements PersonService
{
	private static Boolean isNull = true;
	private static PersonServiceImpl ps = null;
	PersonDaoImpl perDao = new PersonDaoImpl();
	
	public PersonServiceImpl()
	{
		isNull = false;
	}
	
	public static synchronized PersonServiceImpl getInstance()
	{
		
		if (isNull == true)
		{
			ps = new PersonServiceImpl();
		}
		
		return ps;
	}

	@Override
	public void createPerson(Person per)
	{
		// TODO Auto-generated method stub
		perDao.insertPerson(per);
	}

	@Override
	public List<Person> getEveryone()
	{
		// TODO Auto-generated method stub
		return perDao.getAllPersons();
	}

	@Override
	public List<Person> getUsersByRole(int role)
	{
		// TODO Auto-generated method stub
		return perDao.getPersonsByRole(role);
	}

	@Override
	public Person getUserById(int id)
	{
		// TODO Auto-generated method stub
		return perDao.getPersonById(id);
	}

	@Override
	public Person getUserByUsername(String username)
	{
		// TODO Auto-generated method stub
		return perDao.getPersonByUsername(username);
	}

	@Override
	public void updateUser(Person per)
	{
		// TODO Auto-generated method stub
		perDao.savePerson(per);
	}

	@Override
	public void deleteUser(int id)
	{
		// TODO Auto-generated method stub
		perDao.deletePersonById(id);
	}

}
