package com.revature.samsel_q11a;
/*
 * Parent Class
 */
public class FloatC {
	float a=7f;
	float b;
	float c=9f;
	public float va=3f;
	//No args Constructor
	public FloatC(){
		
	}
	
	//Parameterized constructor
	public FloatC(float iA, float iB){
		this.a=iA;
		this.b=iB;
	}
	
	/**
	 * @return the a
	 */
	public float getA() {
		return a;
	}
	
	/**
	 * @return the b
	 */
	public float getB() {
		return b;
	}
	
	protected float getC(){
		return c;
	}
}
