package hobbs.project01.pojo;

public class GradeFormat {
	
	private Integer id;
	private String grades, passingGrade;
	
	public GradeFormat() {
		super();
	}
	
	public GradeFormat(Integer id, String grades, String passingGrade) {
		super();
		this.id = id;
		this.grades = grades;
		this.passingGrade = passingGrade;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGrades() {
		return grades;
	}
	public void setGrades(String grades) {
		this.grades = grades;
	}
	public String getPassingGrade() {
		return passingGrade;
	}
	public void setPassingGrade(String passingGrade) {
		this.passingGrade = passingGrade;
	}

	@Override
	public String toString() {
		return "GradeFormat [id=" + id + ", grades=" + grades + ", passingGrade=" + passingGrade + "]";
	}

}
