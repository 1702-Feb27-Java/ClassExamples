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
	
}
