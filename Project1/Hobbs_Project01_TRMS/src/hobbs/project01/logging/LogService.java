package hobbs.project01.logging;

/**
 * Services access to the database to access logs.
 * 
 * @author Michael Hobbs
 *
 */
public interface LogService {

	/**
	 * Logs a message with the given level.
	 * 
	 * @param level
	 * @param message
	 */
	public void log(Log.Level level, String message);
	
	/**
	 * Convenience method to log a message with the default log level.
	 * @param message
	 */
	public void log(String message);
	
	/**
	 * Logs a message with the TRACE level.
	 * 
	 * @param message
	 */
	public void trace(String message);
	
	/**
	 * Logs a message with the DEBUG level.
	 * 
	 * @param message
	 */
	public void debug(String message);
	
	/**
	 * Logs a message with the INFO level.
	 * 
	 * @param message
	 */
	public void info(String message);
	
	/**
	 * Logs a message with the WARN level.
	 * 
	 * @param message
	 */
	public void warn(String message);
	
	/**
	 * Logs a message with the ERROR level.
	 * 
	 * @param message
	 */
	public void error(String message);
	
	/**
	 * Logs a message with the FATAL level.
	 * 
	 * @param message
	 */
	public void fatal(String message);
	
}
