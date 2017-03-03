//David Lund
package homework.questionseven;
import java.util.Arrays;
import java.util.Comparator;

public class QuestionSeven implements Comparator<Person> {

	public static void main(String[] args) 
	
	{
		//create objec
		Person[] p= new Person [2];
		p[0]= new Person("Mike","Sales",23);
		p[1]= new Person("Sally,","Human Resources",34);
		///System.out.println(p[1].getAge());
		lineBreaker();
		System.out.println("Sort By Age");
		//use arrays utility to 
		Arrays.sort(p,QuestionSeven.AgeComparator);
		displayList(p);
		lineBreaker();
		System.out.println("Sort By Department");
		Arrays.sort(p,QuestionSeven.DepartmentComparator);
		displayList(p);
		lineBreaker();
		System.out.println("Sort By Name");
		
		Arrays.sort(p,QuestionSeven.NameComparator);
		displayList(p);
		lineBreaker();
		
	}

	
	public static void lineBreaker()
	{
		
		System.out.println("-------------------------------------");
	}
	public static void displayList(Person p[])
	{
		
		for(int i = 0 ;i <p.length;i++)
		{
		
			System.out.println("-Name : "+ p[i].getName()+" | Age : "+ p[i].getAge()+" | Department : "+p[i].getDepartment());
		}
	}
	  public static Comparator<Person> AgeComparator = new Comparator<Person>() {

	        @Override
	        public int compare(Person p1, Person  p2) {
	            return (int) (p1.getAge() - p2.getAge());
	        }
	    };
	    public static Comparator<Person> NameComparator = new Comparator<Person>() {

	        @Override
	        public int compare(Person p1, Person  p2) {
	          return p1.getName().compareTo(p2.getName());
	        }
	    };
	    public static Comparator<Person> DepartmentComparator = new Comparator<Person>() {

	        @Override
	        public int compare(Person p1, Person  p2) {
	        	  return p1.getDepartment().compareTo(p2.getDepartment());
	        }
	    };
		@Override
		public int compare(Person arg0, Person arg1) {
			// TODO Auto-generated method stub
			return 0;
		}
}
class Person 
{
	
	private int age;
	private String department;
	private String name;
	
	public Person(String name,String department,int age)
	{
		
		this.age = age;
		this.name = name;
		this.department= department;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
