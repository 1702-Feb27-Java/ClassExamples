package JDBC;
import java.util.List;

import Users.*;

public interface PersonDAO
{
	//////   CREATE
	public void insertPerson(Person per);
	
	/////    READ
	public List<Person> getAllPersons();
	public List<Person> getPersonsByRole(int role);
	public Person getPersonById(int id);
	public Person getPersonByUsername(String username);
	
	//////    UPDATE
	public void savePerson(Person per);
	
	//////    DELETE
	public void deletePersonById(int id);
	
	
}
