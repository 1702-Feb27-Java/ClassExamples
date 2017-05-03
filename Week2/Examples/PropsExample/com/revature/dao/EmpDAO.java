package com.revature.dao;

import java.util.List;

import com.revature.pojo.Employee;

/**
 * 
 * @author Ryan
 *
 *This is the interface that will be implemented for interactions with the database.
 *
 */
public interface EmpDAO {
	public Employee getEmpByID(int id);
	public Employee getEmpByFName(String fname);
	public List<Employee> getEmpsByTitle(String title);
	public void insertEmp(Employee e);
}
