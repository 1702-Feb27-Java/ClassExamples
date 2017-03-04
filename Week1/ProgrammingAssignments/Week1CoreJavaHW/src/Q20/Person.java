package Q20;

public class Person {
public Person (String first, String last, int age, String state){
	
	
	this.first = first;
	this.last = last;
	this.age = age;
	this.state = state;
	
}
		public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
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
		private String first;
		private String last;
		private int age;
		private String state;
}
