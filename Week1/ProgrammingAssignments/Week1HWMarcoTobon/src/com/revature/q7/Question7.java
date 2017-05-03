package com.revature.q7;

import java.util.Arrays;

public class Question7
{

	public static void main(String[] args)
	{
		Employee employes [] = new Employee[2];

		employes[0] = new Employee();
		employes[0].setAge(10);
		employes[0].setDept("Technology");
		employes[0].setNm("Marco");
		
		employes[1] = new Employee();
		employes[1].setAge(1000000);
		employes[1].setDept("History");
		employes[1].setNm("Jeebus");
	
		//LIST THE ITEMS IN THE ARRAY
		System.out.println("List of employees");
		
		for ( Employee em: employes)
		{
			System.out.println( em.getNm() + "\t" + em.getAge() + "\t" + em.getDept());
		}
		System.out.println();
		
		//SORT THE ITEMS IN THE LIST BY AGE
		Arrays.sort(employes, new AgeComparator());
		
		System.out.println("List of employees sorted by age");
		for ( Employee em: employes)
		{
			System.out.println( em.getNm() + "\t" + em.getAge() + "\t" + em.getDept());
		}
		System.out.println();
		
		//SORT THE ITEMS IN THE LIST BY DEPARTMENT
		Arrays.sort(employes, new DeparmentComparator());
		
		System.out.println("List of employees sorted by department");
		for ( Employee em: employes)
		{
			System.out.println( em.getNm() + "\t" + em.getAge() + "\t" + em.getDept());
		}
		System.out.println();

		//SORT THE ITEMS IN THE LIST BY NAME
		Arrays.sort(employes, new NameComparator());
		
		System.out.println("List of employees sorted by name");
		for ( Employee em: employes)
		{
			System.out.println( em.getNm() + "\t" + em.getAge() + "\t" + em.getDept());
		}
		
	}
	

}
