//David Lund
package homework.questionsixteen;

public class QuestionSixteen {

	public static void main(String[] args) {
		//count length of arg and print out the 
		int count=0;
		for(String x: args)
		{
			count += x.length();
			
		}
		System.out.println("The length of args[] is ->" +count);
		
	}

}
