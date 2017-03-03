//David Lund
package homework.questionthirteen;

public class QuestionThirteen {

	public static void main(String[] args) {
		
		int ii = 0;
		

		for (int i = 1 ; i <=4 ; i++)
		{
			
			
			for (int j = 1 ;j<=i;j++)
			{
				//if 0 change to print to 1 
				// print 
				System.out.print(ii+" ");
				if (ii == 0)
				{
					ii = 1;
					
				}
				else
				{
					ii = 0 ;
				}
				
				
			}
			System.out.println("");
			
		}
	}

}
