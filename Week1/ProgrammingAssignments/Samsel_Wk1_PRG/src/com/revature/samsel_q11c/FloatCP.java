package com.revature.samsel_q11c;
/*
 * SubClass inherits FloatC
 */
import com.revature.samsel_q11a.FloatC;

public class FloatCP extends FloatC{
	public float va=5f;
	float c;
	public FloatCP(){
		this.c = super.getC();
	}

	public float getC(){
	 return  c;
	}

}
