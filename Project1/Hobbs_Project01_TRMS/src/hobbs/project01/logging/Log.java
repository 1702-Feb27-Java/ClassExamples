package hobbs.project01.logging;

/**
 * A log that has a message and a level which categorizes it. 
 * 
 * @author Michael Hobbs
 *
 */
public class Log {

	/**
	 * Log levels modeled after log4j.
	 * 
	 * @author Michael Hobbs
	 *
	 */
	public enum Level {
		all(1), trace(2), debug(3), info(4), warn(5), error(6), fatal(7), off(8);
		
		private int id;
		
		private Level(int id) {
			this.id = id;
		}
		
		public int getId() {
			return this.id;
		}
		
		/**
		 * Returns the Level identified by id.
		 * 
		 * @param id the id of the Level
		 * @return the Level
		 */
		public static Level getLevel(int id)
        {
			Level[] levels = Level.values();
            for(int i = 0; i < levels.length; i++)
            {
                if(levels[i].id  == id) {
                	return levels[i];
                }
            }
            return null;
        }
		
		/**
		 * Returns the Level identified by name.
		 * 
		 * @param level the name of the Level
		 * @return the Level
		 */
		public static Level getLevel(String level) {
			Level[] levels = Level.values();
			for(int i = 0; i < levels.length; i++) {
				if (levels[i].toString().equals(levels)) {
					return levels[i];
				}
			}
			return null;
		}
	}
	
	private Integer id;
	private Level level;
	private String message;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
