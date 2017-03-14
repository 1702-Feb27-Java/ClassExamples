package com.revature.banking2.logging;

public interface LogDao {

	void addLog(Log.Level level, String message);
	
}
