package com.revature.dao;

import org.springframework.data.repository.CrudRepository;

import com.revature.beans.Employee;
/*
 * Standard Spring Data Repository interface.
 */
public interface EmployeeRepository extends CrudRepository<Employee, Integer>
{

}
