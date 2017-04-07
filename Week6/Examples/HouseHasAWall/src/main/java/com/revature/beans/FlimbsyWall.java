package com.revature.beans;

import org.springframework.stereotype.Component;

import com.revature.beans.Wall;

@Component
public class FlimbsyWall implements Wall {

	String material = "cards";
	
	@Override
	public String getMaterial() {
		// TODO Auto-generated method stub
		return "Oh No, my house has fallen down";
	}

	@Override
	public void setMaterial(String material) {
		// TODO Auto-generated method stub
		this.material = "cheap " + material;

	}
	
	
public FlimbsyWall(String s){}
	
	public FlimbsyWall() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "FlimbsyWall [material=" + material + "]";
	}

}
