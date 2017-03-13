package com.revature.bankingapp.database.model;

public class AccountType {
	@Override
	public String toString() {
		return "AccountType [typeId=" + typeId + ", type=" + type + "]";
	}
	public AccountType(int typeId, String type) {
		super();
		this.typeId = typeId;
		this.type = type;
	}
	final private int typeId;
	final private String type;
	public int getTypeId() {
		return typeId;
	}
	public String getType() {
		return type;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountType other = (AccountType) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (typeId != other.typeId)
			return false;
		return true;
	}


}
