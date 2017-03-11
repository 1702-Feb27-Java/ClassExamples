package com.revature.q11.access;

// import or full class path needed to get class from outside package
import com.revature.q11.variables.Variables;
/**
 * 
 * @author Aaron Camm
 *
 */
public class Access {
	
	Variables variables;
	
	public Access(){
		variables = new Variables();
		
	}
	
	/**
	 * 
	 * @return the field a from its Variables object
	 */
	public float getA(){
		return variables.a;
	}
	
	/**
	 * 
	 * @return the field b from its Variables object
	 */
	public float getB(){
		return variables.b;
	}
}
