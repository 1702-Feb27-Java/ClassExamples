package com.revature.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value="prototype")
public class HouseImpl implements House {

	private String name;
	
	@Autowired
	@Qualifier("wallImpl")
	private Wall wallid;
	
	public HouseImpl(){}
	
	@Override
	public String toString() {
		return "HouseImpl [name=" + name + ", wall=" + wallid + "]";
	}

	public Wall getWallid() {
		return wallid;
	}

	public void setWallid2(Wall wallid) {
		this.wallid = wallid;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

}
