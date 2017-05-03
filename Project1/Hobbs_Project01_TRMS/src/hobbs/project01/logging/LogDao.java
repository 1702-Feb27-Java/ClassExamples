package hobbs.project01.logging;

/**
 * 
 * @author Michael Hobbs
 *
 */
public interface LogDao {

	void addLog(Log.Level level, String message);
	
}
