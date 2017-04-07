package Employee;

public class Employees {
	
public Employees(String fIRST_NAME, String lAST_NAME, String aDDRESS, int rEPORTS_TO_ID, int dEPT_ID, int rOLE_ID,
			int eMP_ID, String uSERNAME, String pASS, String eMAIL, int rEIN_ALLOWANCE, int am) {
		super();
		FIRST_NAME = fIRST_NAME;
		LAST_NAME = lAST_NAME;
		ADDRESS = aDDRESS;
		REPORTS_TO_ID = rEPORTS_TO_ID;
		DEPT_ID = dEPT_ID;
		ROLE_ID = rOLE_ID;
		EMP_ID = eMP_ID;
		USERNAME = uSERNAME;
		PASS = pASS;
		EMAIL = eMAIL;
		REIN_ALLOWANCE = rEIN_ALLOWANCE;
		approvedammount = am;
	}

private int approvedammount;
public int getApprovedammount() {
	return approvedammount;
}
public void setApprovedammount(int approvedammount) {
	this.approvedammount = approvedammount;
}

private	String FIRST_NAME;
public String getFIRST_NAME() {
	return FIRST_NAME;
}
public void setFIRST_NAME(String fIRST_NAME) {
	FIRST_NAME = fIRST_NAME;
}
public String getLAST_NAME() {
	return LAST_NAME;
}
public void setLAST_NAME(String lAST_NAME) {
	LAST_NAME = lAST_NAME;
}
public String getADDRESS() {
	return ADDRESS;
}
public void setADDRESS(String aDDRESS) {
	ADDRESS = aDDRESS;
}
public int getREPORTS_TO_ID() {
	return REPORTS_TO_ID;
}
public void setREPORTS_TO_ID(int rEPORTS_TO_ID) {
	REPORTS_TO_ID = rEPORTS_TO_ID;
}
public int getDEPT_ID() {
	return DEPT_ID;
}
public void setDEPT_ID(int dEPT_ID) {
	DEPT_ID = dEPT_ID;
}
public int getROLE_ID() {
	return ROLE_ID;
}
public void setROLE_ID(int rOLE_ID) {
	ROLE_ID = rOLE_ID;
}
public int getEMP_ID() {
	return EMP_ID;
}
public void setEMP_ID(int eMP_ID) {
	EMP_ID = eMP_ID;
}
public String getUSERNAME() {
	return USERNAME;
}
public void setUSERNAME(String uSERNAME) {
	USERNAME = uSERNAME;
}
public String getPASS() {
	return PASS;
}
public void setPASS(String pASS) {
	PASS = pASS;
}
public String getEMAIL() {
	return EMAIL;
}
public void setEMAIL(String eMAIL) {
	EMAIL = eMAIL;
}
public int getREIN_ALLOWANCE() {
	return REIN_ALLOWANCE;
}
public void setREIN_ALLOWANCE(int rEIN_ALLOWANCE) {
	REIN_ALLOWANCE = rEIN_ALLOWANCE;
}
private String	LAST_NAME;
@Override
public String toString() {
	return "Employees [FIRST_NAME=" + FIRST_NAME + ", LAST_NAME=" + LAST_NAME + ", ADDRESS=" + ADDRESS
			+ ", REPORTS_TO_ID=" + REPORTS_TO_ID + ", DEPT_ID=" + DEPT_ID + ", ROLE_ID=" + ROLE_ID + ", EMP_ID="
			+ EMP_ID + ", USERNAME=" + USERNAME + ", PASS=" + PASS + ", EMAIL=" + EMAIL + ", REIN_ALLOWANCE="
			+ REIN_ALLOWANCE + "]";
}
private String	ADDRESS;
private int	REPORTS_TO_ID;
private int	DEPT_ID;
private	int ROLE_ID;
private	int EMP_ID;
private String	USERNAME;
private	String PASS;
private	String EMAIL;
private	int REIN_ALLOWANCE;

}
