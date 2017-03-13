package com.revature.designpats;

public class ShapeFactory {
	public Shape retrieveShape(String s){
		if(s.toLowerCase().equals("circle")){
			return new Circle();
		}
		if(s.toLowerCase().equals("square")){
			return new Square();
		}
		return null;
	}
}
