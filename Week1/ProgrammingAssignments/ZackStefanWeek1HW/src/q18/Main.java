package q18;

public class Main {

	public static void main(String[] args) {
		ConcreteSubclass subClass = new ConcreteSubclass();
		//check if uppercase detection is working
		System.out.println("checking for upper case letters in: Hello " + subClass.checkUppercase("Hello"));
		System.out.println("checking for upper case letters in: hello " + subClass.checkUppercase("hello"));
		
		//check if converting to uppercase is working 
		System.out.println("Changing to all upper case letters in: Hello " + subClass.convertLowercase("Hello"));
		System.out.println("Changing to all upper case letters in: hello " + subClass.convertLowercase("hello"));
		
		//check if converting to int and adding 10 is working
		System.out.println("Converting 95 to a string and adding 10: " + subClass.stringToInt("95"));

	}

}
