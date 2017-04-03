package hobbs.project01.logging;

import hobbs.project01.logging.Log.Level;

/**
 * 
 * @author Michael Hobbs
 *
 */
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

	public Log.Level getDefaultLevel() {
		return defaultLevel;
	}

	public void setDefaultLevel(Log.Level defaultLevel) {
		this.defaultLevel = defaultLevel;
	}

	@Override
	public void trace(String message) {
		logDao.addLog(Log.Level.trace, message);
	}

	@Override
	public void debug(String message) {
		logDao.addLog(Log.Level.debug, message);
	}

	@Override
	public void info(String message) {
		logDao.addLog(Log.Level.info, message);
	}

	@Override
	public void warn(String message) {
		logDao.addLog(Log.Level.warn, message);
	}

	@Override
	public void error(String message) {
		logDao.addLog(Log.Level.error, message);
	}

	@Override
	public void fatal(String message) {
		logDao.addLog(Log.Level.fatal, message);
	}
	
}
