package com.revature.dao;

import java.util.List;

import com.revature.beans.Bear;
import com.revature.beans.Cave;
import com.revature.beans.HoneyPot;

public interface BearDao {
	
	public Bear getBearById(int bearId);
	
	public void createBear();
	
	public void setBear(Bear bear);
	
	public List<Bear> getAllBears();
	
	public Cave getCaveById(int caveId);
	
	public void setCave(Cave cave);
	
	public List<Cave> getAllCaves();
	
	public HoneyPot getHoneyPotById(int honeyPotId);
	
	public void setHoneyPot(HoneyPot honeyPot);
	
	public List<HoneyPot> getAllHoneyPots();
	
	public Bear getOrLoad(int id);
	
	public void criteriaDemo();
	
	public void queryDemo();
	
	public void testCache();

}

