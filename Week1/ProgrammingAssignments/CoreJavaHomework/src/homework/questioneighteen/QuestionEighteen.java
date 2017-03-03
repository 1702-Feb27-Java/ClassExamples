//David Lund
package homework.questioneighteen;
// is abstract class 
abstract public class QuestionEighteen {

	public static void main(String[] args) {
		// created object
		QuestionEighteenSubclass j = new QuestionEighteenSubclass();
		String s = "Hello World";
		System.out.println("The string is now " + s);
		
		//if returns true  print 
		if(j.upperCaseCheck(s))
		{
			System.out.println("Contains UpperCase");
			
			
		}
		//other wise its not uppercase 
		else
		{
			
			System.out.println("Does not Contain UpperCase");
		}
	
		String ss = j.convertLowerToUpper(s);
		System.out.println("The string is now " + ss +"\nConverting 20 then adding to 10 it");
		System.out.println("The int is now ->"+j.convertStringToInt("20"));
		
	}
	//creating abstract methods
	abstract public boolean upperCaseCheck(char sss);
	abstract public boolean upperCaseCheck(String sss);
	abstract public String convertLowerToUpper(String ss);
	abstract public int convertStringToInt(String s);
	
	
	

}
