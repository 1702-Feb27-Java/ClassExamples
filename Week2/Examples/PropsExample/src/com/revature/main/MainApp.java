package com.revature.main;
import com.revature.dao.EmpDaoImpl;
import com.revature.pojo.Employee;

public class MainApp {
	/**
	 * Main driving class.
	 * I create a pojo to insert into my database.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		EmpDaoImpl myDao = new EmpDaoImpl();
		Employee e = new Employee("Thomas", "Edison", 2, 1);
		myDao.insertEmp(e);
	}
}
