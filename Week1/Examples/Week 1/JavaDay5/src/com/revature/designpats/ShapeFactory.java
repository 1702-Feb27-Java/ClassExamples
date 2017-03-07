package com.revature.designpats;

public class ShapeFactory {
	public Shape retrieveShape(String s){
		if(s.toLowerCase().equals("circle")){
			return new Circle();
		}
		return null;
	}
}
