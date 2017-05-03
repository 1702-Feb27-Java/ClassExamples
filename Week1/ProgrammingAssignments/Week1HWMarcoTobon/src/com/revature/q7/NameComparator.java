package com.revature.q7;

import java.util.Comparator;
/**
 * 
 * @author tobon
 * Compares names using compare method of Comparator
 */
public class NameComparator implements Comparator<Employee>
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
		String name1 = em.getNm();
		String name2 = em2.getNm();
		
		return name1.compareTo(name2);
	}
}
