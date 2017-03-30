package com.revature.main;

import java.util.ArrayList;
import com.revature.pojo.Employee;
import com.revature.pojo.Reimburstment;
import com.revature.service.Service;

public class Main
{
	public static void main(String[] args)
	{
		//Testing the service.dao.getUser
		Service service = new Service();
		Employee emp = service.viewAccount("xpgrogan");
		System.out.println(emp.getFname() + " " + emp.getLname());
		System.out.println(emp.getAddress() + " " + emp.getEmail());
		
		//Testing the service.dao.getAllUsers
		ArrayList<Employee> array = new ArrayList();
		array = service.viewAllAccounts();
		for(Employee e : array)
		{
			System.out.println(e.getUsername() + " " + e.getAddress());
		}
		
		ArrayList<Reimburstment> reims = new ArrayList();
		reims = service.viewAllReimburstments();
		for(Reimburstment r : reims)
		{
			System.out.println(r.getEmp_id());
		}
	}
}
