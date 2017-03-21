package com.revature.pojo;

/**
  * Employee Value Object.
  * This class is value object representing database table EMPLOYEES
  * This class is intended to be used together with associated Dao object.
  */
public class Employee {

    /** 
     * Persistent Instance variables. This data is directly 
     * mapped to the columns of database table.
     */
    private int employeeId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private int reportsTo;
    private int roleId;
    private int deptId;



    /** 
     * Two Constructors.
     * The first one takes no arguments and provides the most simple
     * way to create object instance. The another one takes one
     * argument, which is the primary key of the corresponding table.
     */

    public Employee () {

    }

    public Employee (int employeeIdIn) {

          this.employeeId = employeeIdIn;

    }


    /** 
     * Get- and Set-methods for persistent variables.
     */

    public int getEmployeeId() {
          return this.employeeId;
    }
    public void setEmployeeId(int employeeIdIn) {
          this.employeeId = employeeIdIn;
    }

    public String getFirstName() {
          return this.firstName;
    }
    public void setFirstName(String firstNameIn) {
          this.firstName = firstNameIn;
    }

    public String getLastName() {
          return this.lastName;
    }
    public void setLastName(String lastNameIn) {
          this.lastName = lastNameIn;
    }

    public String getUsername() {
          return this.username;
    }
    public void setUsername(String usernameIn) {
          this.username = usernameIn;
    }

    public String getPassword() {
          return this.password;
    }
    public void setPassword(String passwordIn) {
          this.password = passwordIn;
    }

    public String getEmail() {
          return this.email;
    }
    public void setEmail(String emailIn) {
          this.email = emailIn;
    }

    public int getReportsTo() {
          return this.reportsTo;
    }
    public void setReportsTo(int reportsToIn) {
          this.reportsTo = reportsToIn;
    }

    public int getRoleId() {
          return this.roleId;
    }
    public void setRoleId(int roleIdIn) {
          this.roleId = roleIdIn;
    }

    public int getDeptId() {
          return this.deptId;
    }
    public void setDeptId(int deptIdIn) {
          this.deptId = deptIdIn;
    }



    /** 
     * setAll allows to set all persistent variables in one method call.
     * This is useful, when all data is available and it is needed to 
     * set the initial state of this object. Note that this method will
     * directly modify instance variales, without going trough the 
     * individual set-methods.
     */

    public void setAll(int employeeIdIn,
          String firstNameIn,
          String lastNameIn,
          String usernameIn,
          String passwordIn,
          String emailIn,
          int reportsToIn,
          int roleIdIn,
          int deptIdIn) {
          this.employeeId = employeeIdIn;
          this.firstName = firstNameIn;
          this.lastName = lastNameIn;
          this.username = usernameIn;
          this.password = passwordIn;
          this.email = emailIn;
          this.reportsTo = reportsToIn;
          this.roleId = roleIdIn;
          this.deptId = deptIdIn;
    }


    /** 
     * hasEqual-method will compare two Employee instances
     * and return true if they contain same values in all persistent instance 
     * variables. If hasEqual returns true, it does not mean the objects
     * are the same instance. However it does mean that in that moment, they 
     * are mapped to the same row in database.
     */
    public boolean hasEqual(Employee valueEmployee) {

          if (valueEmployee.getEmployeeId() != this.employeeId)
                    return(false);
          return true;
    }



    /**
     * toString will return String object representing the state of this 
     * valueObject. This is useful during application development, and 
     * possibly when application is writing object states in textlog.
     */
    public String toString() {
        String returnString = (""
        		+ "\nclass Employee, mapping to table EMPLOYEES\n"
                + "Persistent attributes: \n" 
                + "employeeId = " + this.employeeId + "\n" 
                + "firstName = " + this.firstName + "\n"
                + "lastName = " + this.lastName + "\n"
                + "username = " + this.username + "\n" 
                + "password = " + this.password + "\n"
                + "email = " + this.email + "\n"
        		+ "reportsTo = " + this.reportsTo + "\n"
        		+ "roleId = " + this.roleId + "\n"
        		+ "deptId = " + this.deptId + "\n");
        return returnString;
    }
}