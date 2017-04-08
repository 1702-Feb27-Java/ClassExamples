package com.revature.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.beans.Employee;
import com.revature.dao.EmployeeRepository;

@Component
@Transactional
public class EmployeeServiceImpl implements EmployeeService
{
	private EmployeeRepository employeeRepository;
	
	/**
	 * Spring Boot will autowire your dependencies when it finds the
	 * appropriate annotations. Otherwise, this should be familiar.
	 */
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository)
	{
		this.employeeRepository=employeeRepository;
	}
	
	@Override
	public void addEmployee(Employee employee) {
		this.employeeRepository.save(employee);		
	}

	@Override
	public void removeEmployee(Employee employee) {
		this.employeeRepository.delete(employee);
	}

	@Override
	public Employee getEmployee(int i) {
		Employee e = this.employeeRepository.findOne(i);
		return e;
	}

	@Override
	public Iterable<Employee> getAllEmployees() {
		return this.employeeRepository.findAll();
	}

	@Override
	public long getCount() {
		return this.employeeRepository.count();
	}

}
