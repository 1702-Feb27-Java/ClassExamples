//David Lund
package homework.questiontwenty;

public class Person {
	public Person(String first,String last,int age,String state){
		
		this.firstName= first;
		this.lastName=last;
		this.age=age;
		this.state=state;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	private String firstName;
	private String lastName;
	private int age;
	private String state;
	
	

}
