package com.revature.banking2.logging;

import com.revature.banking2.logging.Log.Level;

public class LogServiceImpl implements LogService {
	
	private static LogService logService;
	
	private Log.Level defaultLevel;
	
	private LogServiceImpl() {
		defaultLevel = Log.Level.debug;
	}

	@Override
	public void log(Level level, String message) {
		LogDao logger = new LogDaoImpl();
		
		logger.addLog(level, message);
	}

	@Override
	public void log(String message) {
		log(defaultLevel, message);
	}
	
	public static LogService getLogService() {
		if (logService == null) {
			logService = new LogServiceImpl();
		}
		return logService;
	}
	
}
