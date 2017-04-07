package Employee;

public class Messages {
	@Override
	public String toString() {
		return "Messages [message=" + message + ", emp_id=" + emp_id + ", app_id=" + app_id + ", rein_id=" + rein_id
				+ "]";
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public int getApp_id() {
		return app_id;
	}
	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}
	private String message;
	private int emp_id;
	private int app_id;
	private int rein_id;
	public Messages(String message, int emp_id, int app_id, int rein_id) {
		super();
		this.message = message;
		this.emp_id = emp_id;
		this.app_id = app_id;
		this.rein_id = rein_id;
	}
	public int getRein_id() {
		return rein_id;
	}
	public void setRein_id(int rein_id) {
		this.rein_id = rein_id;
	}
	


}
