package database.model;

public class EventType {
	@Override
	public String toString() {
		return "EventType [eventId=" + eventId + ", typeName=" + typeName + ", coverage=" + coverage + "]";
	}
	public EventType(Integer eventId, String typeName, Double coverage) {
		super();
		this.eventId = eventId;
		this.typeName = typeName;
		this.coverage = coverage;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Double getCoverage() {
		return coverage;
	}
	public void setCoverage(Double coverage) {
		this.coverage = coverage;
	}
	public Integer getEventId() {
		return eventId;
	}
	private Integer eventId;
	private String typeName;
	private Double coverage;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eventId == null) ? 0 : eventId.hashCode());
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
		EventType other = (EventType) obj;
		if (eventId == null) {
			if (other.eventId != null)
				return false;
		} else if (!eventId.equals(other.eventId))
			return false;
		return true;
	}
}
