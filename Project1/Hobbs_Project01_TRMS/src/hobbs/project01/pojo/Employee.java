package hobbs.project01.pojo;

import hobbs.project01.pojo.Reimbursement.Status;

public class Employee {
	
	public enum Role {
		sysadmin(1), head(2), supervisor(3), employee(4);
		
		private int id;
		
		private Role(int id) {
			this.id = id;
		}
		
		public int getId() {
			return this.id;
		}
		
		public static Role getRole(int id)
        {
            Role[] roles = Role.values();
            for(int i = 0; i < roles.length; i++)
            {
                if(roles[i].id  == id) {
                	return roles[i];
                }
            }
            return null;
        }
		
		public static Role getRole(String role) {
			Role[] roles = Role.values();
			for(int i = 0; i < roles.length; i++) {
				if (roles[i].toString().equals(role)) {
					return roles[i];
				}
			}
			return null;
		}
	}
	
	public enum Department {
		RD(1), IT(2), PR(3), HR(4), Benco(5);
		
		private int id;
		
		private Department(int id) {
			this.id = id;
		}
		
		public int getId() {
			return this.id;
		}
		
		public static Department getDepartment(int id)
        {
            Department[] departments = Department.values();
            for(int i = 0; i < departments.length; i++)
            {
                if(departments[i].id  == id) {
                	return departments[i];
                }
            }
            return null;
        }
		
		public static Department getDepartment(String department) {
			Department[] departments = Department.values();
			for(int i = 0; i < departments.length; i++) {
				if (departments[i].toString().equals(department)) {
					return departments[i];
				}
			}
			return null;
		}
	}

	private Integer id, roleId, supervisorId, departmentId;
	private String username, password, email, firstName, lastName;
	
	public Employee() {
		
	}
	
	public Employee(Integer id, Integer roleId, Integer supervisorId, Integer departmentId, String username,
			String password, String email, String firstName, String lastName) {
		super();
		this.id = id;
		this.roleId = roleId;
		this.supervisorId = supervisorId;
		this.departmentId = departmentId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	public Integer getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(Integer supervisorId) {
		this.supervisorId = supervisorId;
	}
	
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", roleId=" + roleId + ", supervisorId=" + supervisorId + ", departmentId="
				+ departmentId + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
}
