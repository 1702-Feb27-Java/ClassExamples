package com.revature.q7;

import java.util.Comparator;
/**
 * 
 * @author tobon
 * Compares ages using compare method of Comparator
 */
public class AgeComparator implements Comparator<Employee>
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
		return em.getAge() - em2.getAge();
	}
}
