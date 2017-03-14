package com.revature.banking2.logging;

import com.revature.banking2.logging.Log.Level;

public class LogServiceImpl implements LogService {
	
	private static LogDao logDao;
	private static LogService logService;
	
	private Log.Level defaultLevel;
	
	private LogServiceImpl() {
		logDao = new LogDaoImpl();
		defaultLevel = Log.Level.debug;
	}

	@Override
	public void log(Level level, String message) {
		logDao.addLog(level, message);
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
