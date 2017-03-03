package com.revature.q7;
/**
 * 
 * @author tobon
 * Employee class with variables 
 * age
 * name
 * department
 */
class Employee 
{
	private int age;
	private String nm;
	private String dept;
	
	//Consturctors
	Employee (){}
	
	//Constructor with Arguments
	Employee ( int a, String n, String d)
	{
		age= a;
		nm=n;
		dept=d;
	}
	
	//SETTERS AND GETTERS
	public int getAge() 
	{
		return age;
	}

	public void setAge(int age) 
	{
		this.age = age;
	}

	public String getNm() 
	{
		return nm;
	}

	public void setNm(String nm) 
	{
		this.nm = nm;
	}

	public String getDept() 
	{
		return dept;
	}

	public void setDept(String dept) 
	{
		this.dept = dept;
	}

	
}
