package com.revature.q11;
import com.revature.q11other.*;

/**
 * 
 * @author marco tobon
 *
 * Access two floats from a different package
 */
public class Question11
{
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		float gotOne = ExternalClass.externalFloat1;
		float gotTwo = ExternalClass.externalFloat2;
		System.out.println(gotOne+gotTwo);

	}

}
