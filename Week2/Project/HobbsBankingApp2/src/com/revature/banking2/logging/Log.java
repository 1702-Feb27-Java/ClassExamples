package com.revature.banking2.logging;

public class Log {

	public enum Level {
		debug(1), info(2), warn(3), error(4), fatal(5);
		
		int levelId;
		
		private Level(int levelId) {
			this.levelId = levelId;
		}
		
		public int getLevelId() {
			return this.levelId;
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
