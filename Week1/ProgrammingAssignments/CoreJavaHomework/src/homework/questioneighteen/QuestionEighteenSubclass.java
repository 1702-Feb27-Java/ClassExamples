//David Lund
package homework.questioneighteen;

public class QuestionEighteenSubclass extends QuestionEighteen{

	@Override
	public boolean upperCaseCheck(String sss) {
		boolean flag = false;
		
	   for(int i = 0 ; i< sss.length();i++)
	   {
		   if(  Character.isUpperCase(sss.charAt(i))==true)
		   {
		 return true;
		   }
	   }
	   return false;
	}

	@Override
	public String convertLowerToUpper(String sss) {
		// TODO Auto-generated method stub
		String sTemp ="";
		 for(int i = 0 ; i< sss.length();i++)
		   {
			   if(  Character.isLowerCase(sss.charAt(i))==true)
			   {
				  sTemp +=Character.toUpperCase( sss.charAt(i));
			   }
			   else
			   {
				   
				   sTemp+= sss.charAt(i);
			   }
		   }
		 ///System.out.println();
		return sTemp;
	}

	@Override
	public int convertStringToInt(String s) {
	int i = 0;
		try
	{
		i = Integer.parseInt(String.valueOf(s));
	}
	catch(Exception e)
	{
	
		
	}

		return  i+10;
	}

	@Override
	public boolean upperCaseCheck(char sss) {
		// TODO Auto-generated method stub
		return false;
	}

}
