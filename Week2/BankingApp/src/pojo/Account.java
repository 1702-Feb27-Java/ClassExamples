package pojo;

public class Account {
	private double balance;
	private int accountId;
	private double accountType;
	private int status;
	private int resolverid;
	public Account(double balance, int accountId, double accountType, int status, int resolverid) {
		super();
		this.balance = balance;
		this.accountId = accountId;
		this.accountType = accountType;
		this.status = status;
		this.resolverid = resolverid;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public double getAccountType() {
		return accountType;
	}
	public void setAccountType(double accountType) {
		this.accountType = accountType;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getResolverid() {
		return resolverid;
	}
	public void setResolverid(int resolverid) {
		this.resolverid = resolverid;
	}
	
	
}