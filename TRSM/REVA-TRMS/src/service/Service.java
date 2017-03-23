package service;

import java.util.ArrayList;
import objects.Employee;
import dao.DAOObject;

public class Service {
	private DAOObject db;
	
	public Service(){
		db = new DAOObject();
	}
	
	
	public boolean doesUserNameAndPassMatch(String userName, String pass){
		if(!this.doesUserNameExist(userName)){
			return false;
		}
		Employee temp = db.getEmployee(userName);
		if(temp.getPassword().equals(pass)){
			return true;
		}
		return false;
	}
	
	public boolean doesUserNameExist(String userName){
		ArrayList<Employee> list = db.getAllEmployees();
		
		for(Employee e : list){
			if(e.getUserName().equals(userName)){
				return true;
			}
		}
		return false;
	}
	
	public Employee getEmployee(String userName){
		return db.getEmployee(userName);
	}
	

}
