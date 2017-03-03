package com.revature.samsel_q18;

/*Subclass inherits from SOperation */
public class SOperationChild extends SOperation{

	@Override
	public boolean isUpperCaseFound(String iStr) {
		// TODO Auto-generated method stub
		boolean bIsUpperC = false;
		for(int i=0;i<iStr.length();i++){
			char ch = iStr.charAt(i);
			if(Character.isUpperCase(ch))
			{
				bIsUpperC = true;
				break;
			}
		}
		return bIsUpperC;
	}

	@Override
	public String convertToU(String iStr) {
		// TODO Auto-generated method stub
		return iStr.toUpperCase();
	}

	@Override
	public void updateInput(String iStr) {
		// TODO Auto-generated method stub
		boolean bIsInteger = iStr.chars().allMatch(c->c >= 48 && c<=57);//iStr.contains("[0-9+]");
		//System.out.println("Is Integer string : "+bIsInteger);
		if(bIsInteger==true){
			int iVal = Integer.parseInt(iStr);
			iVal+=10;
			System.out.println("METHOD 3 : Updated Value : "+iVal);
		}
		else
			System.out.println("METHOD 3 : Entered string is not a number.");
	}
}
