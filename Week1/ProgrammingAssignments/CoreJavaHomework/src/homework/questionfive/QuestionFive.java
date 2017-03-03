package homework.questionfive;
//David Lund
import java.util.Scanner;

public class QuestionFive {

	// 
	public static void main(String[] args) {
	QuestionFive qf= new QuestionFive();
		String sMessage = "hello world";
		int in =5;
		//get only 5 chars of string
	String str = qf.getStringIndex(in, sMessage	);

	System.out.println(str);
	
	
	
	

	
	
	}
	
	public String getStringIndex(int valueInd, String str)
	{
	   String strN = "";
	//move through index of string and concat to new string up until index
	   
		
		for(int i = 0 ; i < str.length();i++)
		{
			if( i>=0&&i<=valueInd-1)
			{
				
				
				strN+=str.charAt(i);
			}
			
			
		}	
		return strN;
	}
	}
	


