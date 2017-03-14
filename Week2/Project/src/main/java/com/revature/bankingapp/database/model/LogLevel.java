package com.revature.bankingapp.database.model;

public class LogLevel {
	private final int logLevelId;
	private final String logLevel;
	public int getLogLevelId() {
		return logLevelId;
	}
	public String getLogLevel() {
		return logLevel;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + logLevelId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogLevel other = (LogLevel) obj;
		if (logLevelId != other.logLevelId)
			return false;
		return true;
	}
	public LogLevel(int logLevelId, String logLevel) {
		super();
		this.logLevelId = logLevelId;
		this.logLevel = logLevel;
	}
	
	
}
