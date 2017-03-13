package JDBC;

import java.util.List;

import Users.Person;

public interface PersonService
{

	////// CREATE
	public void createPerson(Person per);

	///// READ
	public List<Person> getEveryone();

	public List<Person> getUsersByRole(int role);

	public Person getUserById(int id);

	public Person getUserByUsername(String username);

	////// UPDATE
	public void updateUser(Person per);

	////// DELETE
	public void deleteUser(int id);

}
