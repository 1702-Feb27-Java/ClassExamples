package com.week1homework.question7;

import java.util.Comparator;

public class EmployeeSorter 
{
	
	private String name;
	private String department;
	private int age;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDepartment()
	{
		return department;
	}

	public void setDepartment(String department)
	{
		this.department = department;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

		EmployeeSorter employee1 = new EmployeeSorter();
		EmployeeSorter employee2 = new EmployeeSorter();
		
		employee1.setName("Ben");
		employee1.setDepartment("Marketing");
		employee1.setAge(23);
		
		employee2.setName("Ben");
		employee2.setDepartment("Accounting");
		employee2.setAge(33);
		
		System.out.println("Unsorted: \n");
		ShowEmployee(employee1);
		ShowEmployee(employee2);
		
		
		System.out.println("Sorted: \n");
		
		SortingClass s = new SortingClass();
		if (s.compare(employee1, employee2) < 0)
		{
			ShowEmployee(employee1);
			ShowEmployee(employee2);
		}
		else
		{
			ShowEmployee(employee2);
			ShowEmployee(employee1);
		}
	}
	
	private static void ShowEmployee(EmployeeSorter arg0)
	{
		System.out.println(arg0.getName());
		System.out.println(arg0.getDepartment());
		System.out.println(arg0.getAge());
	}

}

class SortingClass implements Comparator<EmployeeSorter>
{

	@Override
	public int compare(EmployeeSorter arg0, EmployeeSorter arg1)
	{
		// TODO Auto-generated method stub
		
		int nameDiff = (arg0.getName()).compareTo(arg1.getName());
		int deptDiff = (arg0.getDepartment()).compareTo(arg1.getDepartment());
		
		if (nameDiff == 0 && deptDiff == 0)
		{
			return arg0.getAge() - arg1.getAge();
		}
		else if (nameDiff == 0 && deptDiff != 0)
		{
			return deptDiff;
		}
		else if (nameDiff != 0)
		{
			return nameDiff;
		}
		
		return 0;
	}
	
}
