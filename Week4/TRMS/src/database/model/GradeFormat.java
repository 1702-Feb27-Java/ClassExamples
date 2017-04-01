package database.model;

public class GradeFormat {

	public GradeFormat(Integer formatId, String formantName, Boolean requiresPresentation) {
		super();
		this.formatId = formatId;
		this.formatName = formantName;
		this.requiresPresentation = requiresPresentation;
	}
	Integer formatId;
	@Override
	public String toString() {
		return "GradeFormat [formatId=" + formatId + ", formatName=" + formatName + ", requiresPresentation="
				+ requiresPresentation + "]";
	}
	String formatName;
	Boolean requiresPresentation;
	public Boolean getRequiresPresentation() {
		return requiresPresentation;
	}
	public void setRequiresPresentation(Boolean requiresPresentation) {
		this.requiresPresentation = requiresPresentation;
	}
	public String getFormatName() {
		return formatName;
	}
	public void setFormatName(String formantName) {
		this.formatName = formantName;
	}
	public Integer getFormatId() {
		return formatId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((formatId == null) ? 0 : formatId.hashCode());
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
		GradeFormat other = (GradeFormat) obj;
		if (formatId == null) {
			if (other.formatId != null)
				return false;
		} else if (!formatId.equals(other.formatId))
			return false;
		return true;
	}
}
