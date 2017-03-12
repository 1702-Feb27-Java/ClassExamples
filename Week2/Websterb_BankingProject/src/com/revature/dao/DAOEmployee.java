package com.revature.dao;

import java.util.ArrayList;

public interface DAOEmployee {
	int addEmployee(String fn, String ln, String un, String pw); 
	
	ArrayList<String> loginEmployee(String un, String pw);
}
