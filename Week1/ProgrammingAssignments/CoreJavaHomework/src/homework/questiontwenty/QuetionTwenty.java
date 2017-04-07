//David Lund
package homework.questiontwenty;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class QuetionTwenty {

	public static void main(String[] args) throws Exception {
		boolean flagquit = false;
		BufferedReader br=null;
	
		try{
			//catch error if no data.txt exists
			 br = new BufferedReader(new FileReader("Data.txt"));
		}
		catch(Exception eee)
		{
			System.out.println("quiting \n" + eee.getMessage());
			flagquit=true;
		}
		if(flagquit!=true)
		{
		
		ArrayList<Person>  alPerson= new ArrayList();
		try 
		{
			while (true)
			{
			//	System.out.println("In");
			String str = "";
			str=br.readLine();
	        String[] strarr = str.split(":");
	          alPerson.add(new Person(strarr[0],strarr[1],Integer.parseInt(strarr[2]),strarr[3]));
			
		
			}
			
			
			
		}
		catch(Exception e)
		{
			
			
		}
		finally
		{
			try {
				
				br.close();
			}
			catch(Exception ee)
			{
				
				
			}
			
			
		}	

		
		for(Person a:alPerson)
		{
			
			if(a.getFirstName().equals("Mikey"))
					{
				
				System.out.println("Name : "+a.getFirstName() + " "+a.getLastName());
				System.out.println("Age : "+a.getAge() + " years");
				System.out.println("State: "+ a.getState() + " State");
				
					}
		}
		}
	}

}
