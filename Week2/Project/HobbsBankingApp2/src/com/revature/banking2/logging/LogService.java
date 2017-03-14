package com.revature.banking2.logging;

public interface LogService {

	public void log(Log.Level level, String message);
	
	/**
	 * Logs with the default log level.
	 * @param message
	 */
	public void log(String message);
	
}
