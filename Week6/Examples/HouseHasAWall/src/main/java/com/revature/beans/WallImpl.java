package com.revature.beans;

import org.springframework.stereotype.Component;

@Component("goodWall")
public class WallImpl implements Wall {

	private String material = "Wood";
	
	public WallImpl() {}
	
	@Override
	public String getMaterial() {
		// TODO Auto-generated method stub
		return material;
	}

	@Override
	public void setMaterial(String material) {
		// TODO Auto-generated method stub
		this.material = material;
	}

	@Override
	public String toString() {
		return "WallImpl [material=" + material + "]";
	}

}
