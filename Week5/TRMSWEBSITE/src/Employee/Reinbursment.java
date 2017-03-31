package Employee;

public class Reinbursment {
	
	
	
	
	private int coursecost;
	
	public int getCoursecost() {
		return coursecost;
	}
	public void setCoursecost(int coursecost) {
		this.coursecost = coursecost;
	}
	@Override
	public String toString() {
		return "Reinbursment [EMP_ID=" + EMP_ID + ", COR_LOCATION=" + COR_LOCATION + ", APP_DATE=" + APP_DATE
				+ ", COR_STARTDATE=" + COR_STARTDATE + ", COR_ENDDATE=" + COR_ENDDATE + ", COR_TIME=" + COR_TIME
				+ ", REIN_ID=" + REIN_ID + ", APP_ID=" + APP_ID + ", COR_ID=" + COR_ID + ", GRADE_ID=" + GRADE_ID
				+ ", GRADE=" + GRADE + "]";
	}
	public Reinbursment(int eMP_ID, String cOR_LOCATION, String aPP_DATE, String cOR_STARTDATE, String cOR_ENDDATE,
			String cOR_TIME, int rEIN_ID, int aPP_ID, int cOR_ID, int gRADE_ID, String gRADE,int Coursecost) {
		super();
		EMP_ID = eMP_ID;
		COR_LOCATION = cOR_LOCATION;
		APP_DATE = aPP_DATE;
		COR_STARTDATE = cOR_STARTDATE;
		COR_ENDDATE = cOR_ENDDATE;
		COR_TIME = cOR_TIME;
		REIN_ID = rEIN_ID;
		APP_ID = aPP_ID;
		COR_ID = cOR_ID;
		GRADE_ID = gRADE_ID;
		GRADE = gRADE;
		coursecost = Coursecost;
	}
	public int getEMP_ID() {
		return EMP_ID;
	}
	public void setEMP_ID(int eMP_ID) {
		EMP_ID = eMP_ID;
	}
	public String getCOR_LOCATION() {
		return COR_LOCATION;
	}
	public void setCOR_LOCATION(String cOR_LOCATION) {
		COR_LOCATION = cOR_LOCATION;
	}
	public String getAPP_DATE() {
		return APP_DATE;
	}
	public void setAPP_DATE(String aPP_DATE) {
		APP_DATE = aPP_DATE;
	}
	public String getCOR_STARTDATE() {
		return COR_STARTDATE;
	}
	public void setCOR_STARTDATE(String cOR_STARTDATE) {
		COR_STARTDATE = cOR_STARTDATE;
	}
	public String getCOR_ENDDATE() {
		return COR_ENDDATE;
	}
	public void setCOR_ENDDATE(String cOR_ENDDATE) {
		COR_ENDDATE = cOR_ENDDATE;
	}
	public String getCOR_TIME() {
		return COR_TIME;
	}
	public void setCOR_TIME(String cOR_TIME) {
		COR_TIME = cOR_TIME;
	}
	public int getREIN_ID() {
		return REIN_ID;
	}
	public void setREIN_ID(int rEIN_ID) {
		REIN_ID = rEIN_ID;
	}
	public int getAPP_ID() {
		return APP_ID;
	}
	public void setAPP_ID(int aPP_ID) {
		APP_ID = aPP_ID;
	}
	public int getCOR_ID() {
		return COR_ID;
	}
	public void setCOR_ID(int cOR_ID) {
		COR_ID = cOR_ID;
	}
	public int getGRADE_ID() {
		return GRADE_ID;
	}
	public void setGRADE_ID(int gRADE_ID) {
		GRADE_ID = gRADE_ID;
	}
	public String getGRADE() {
		return GRADE;
	}
	public void setGRADE(String gRADE) {
		GRADE = gRADE;
	}
	private int EMP_ID;
	private String COR_LOCATION;
	private String APP_DATE;
	private String COR_STARTDATE;
	private String COR_ENDDATE;
	private String COR_TIME;
	private int REIN_ID;
	private int APP_ID;
	private int COR_ID;
	private int GRADE_ID;
	private String GRADE;

}
