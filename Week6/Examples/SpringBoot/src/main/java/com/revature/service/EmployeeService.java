package com.revature.service;

import com.revature.beans.Employee;

public interface EmployeeService
{
	public void addEmployee(Employee employee);
	public void removeEmployee(Employee employee);
	public Employee getEmployee(int i);
	public Iterable<Employee> getAllEmployees();
	public long getCount();
}
