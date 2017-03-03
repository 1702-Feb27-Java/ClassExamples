package com.revature.q7;

import java.util.Comparator;
/**
 * 
 * @author tobon
 * Compares the department of the employee
 */
public class DeparmentComparator implements Comparator<Employee>
{
	@Override
	/**
	 * @param em 
	 * @param em2
	 * 
	 * em is the first employee
	 * em2 is the second employee
	 */
	public int compare(Employee em, Employee em2)
	{
		String dept1 = em.getDept();
		String dept2 = em2.getDept();
		
		return dept1.compareTo(dept2);
	}
}
