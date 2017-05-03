package Q7;

import java.util.*;

public class Main
{
	public static void main(String[] args)
	{
		//Create to objects of Person type, initializes them, and then populates an ArrayList with them
		Person person1 = new Person();
		Person person2 = new Person();
		ArrayList<Person> array = new ArrayList();
		
		person1.setName("Bill");
		person1.setID(25520);
		person1.setAge(25);
		
		person2.setName("Ann");
		person2.setID(12345);
		person2.setAge(22);
		
		array.add(person1);
		array.add(person2);
		
		//This is the actual sorting calls, first is by name, second is by age, and third is by ID
		Collections.sort(array, Person.NAME_ORDER);
		System.out.println("Sorted by name in descending order");
		for(Person p : array)
		{
			System.out.println(p.getName() + " " + p.getAge() + " " + p.getID());
		}
		
		Collections.sort(array, Person.AGE_ORDER);
		System.out.println("\nSorted by age in ascending order");
		for(Person p : array)
		{
			System.out.println(p.getName() + " " + p.getAge() + " " + p.getID());
		}
		
		Collections.sort(array, Person.ID_ORDER);
		System.out.println("\nSorted by ID in descending order");
		for(Person p : array)
		{
			System.out.println(p.getName() + " " + p.getAge() + " " + p.getID());
		}
	}
}