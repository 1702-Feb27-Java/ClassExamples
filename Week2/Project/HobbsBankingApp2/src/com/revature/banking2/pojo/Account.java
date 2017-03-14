package com.revature.banking2.pojo;

public class Account {
	
	/**
	 * The kinds of accounts.
	 * 
	 * @author Michael Hobbs
	 *
	 */
	public enum Type {
		checking(1), savings(2);
		
		int id;
		
		private Type(int id) {
			this.id = id;
		}
		
		public int getId() {
			return this.id;
		}
		
		public static Type getType(int id)
        {
            Type[] types = Type.values();
            for(int i = 0; i < types.length; i++)
            {
                if(types[i].id  == id) {
                	return types[i];
                }
            }
            return null;
        }
	}
	
	/**
	 * The status of accounts.
	 * 
	 * @author Michael Hobbs
	 *
	 */
	public enum Status {
		pending(1), approved(2), denied(3);
		
		int id;
		
		private Status(int id) {
			this.id = id;
		}
		
		public int getId() {
			return this.id;
		}
		
		public static Status getStatus(int id)
        {
            Status[] statuses = Status.values();
            for(int i = 0; i < statuses.length; i++)
            {
                if(statuses[i].id  == id) {
                	return statuses[i];
                }
            }
            return null;
        }
	}
	
	private Integer id;
	private Double balance;
	private Type type;
	private Status status;
	private Integer resolverId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Integer getResolverId() {
		return resolverId;
	}
	public void setResolverId(Integer resolverId) {
		this.resolverId = resolverId;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + ", type=" + type + ", status=" + status + ", resolverId="
				+ resolverId + "]";
	}
	
}
