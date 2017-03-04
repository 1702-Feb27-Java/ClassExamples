package Q7;

import java.util.Comparator;

public class Person implements Comparator<Person>
{
	String name;
	int age, id;
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String n)
	{
		this.name = n;
	}
	
	public int getID()
	{
		return id;
	}
	
	public void setID(int i)
	{
		this.id = i;
	}
	
	public int getAge()
	{
		return age;
	}
	
	public void setAge(int a)
	{
		this.age = a;
	}
	
	//This method orders by name
	final static Comparator<Person> NAME_ORDER = new Comparator<Person>()
	{
		@Override
		public int compare(Person o1, Person o2)
		{
			String person1Name = o1.getName().toUpperCase();
			String person2Name = o2.getName().toUpperCase();
			return person2Name.compareTo(person1Name);
		}
	};
	
	//This method orders by age
	final static Comparator<Person> AGE_ORDER = new Comparator<Person>()
	{
		@Override
		public int compare(Person o1, Person o2)
		{
			Integer person1Age = o1.getAge();
			Integer person2Age = o2.getAge();
			return person1Age.compareTo(person2Age);
		}
	};
	
	//This method orders by ID
	final static Comparator<Person> ID_ORDER = new Comparator<Person>()
	{
		@Override
		public int compare(Person o1, Person o2)
		{
			Integer person1ID = o1.getID();
			Integer person2ID = o2.getID();
			return person2ID.compareTo(person1ID);
		}
	};

	@Override
	public int compare(Person o1, Person o2)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	
}
