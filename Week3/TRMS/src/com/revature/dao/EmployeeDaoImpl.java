package com.revature.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.pojo.Employee;
import com.revature.sql.ConnectionFactory;


 /**
  * Employee Data Access Object (DAO).
  * This class contains all database handling that is needed to 
  * permanently store and retrieve Employee object instances. 
  */

public class EmployeeDaoImpl {

    /**
     * getObject-method. This will create and load valueObject contents from database 
     * using given Primary-Key as identifier. This method is just a convenience method 
     * for the real load-method which accepts the valueObject as a parameter. Returned
     * valueObject will be created using the createValueObject() method.
     */
    public Employee getEmployee(int employeeId) {
    	
    	Employee employee = null;
    	try(Connection conn = ConnectionFactory.getConnection()) {
    		conn.setAutoCommit(false);	
            String sql = "SELECT * FROM EMPLOYEES WHERE (EMPLOYEE_ID = ? ) "; 
            PreparedStatement ps = null;
            ps = conn.prepareStatement(sql);
			employee = new Employee();
			employee.setEmployeeId(employeeId);
            ps.setInt(1, employee.getEmployeeId()); 
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                 employee.setFirstName(result.getString("FIRST_NAME")); 
                 employee.setLastName(result.getString("LAST_NAME")); 
                 employee.setUsername(result.getString("USERNAME")); 
                 employee.setPassword(result.getString("PASSWORD")); 
                 employee.setEmail(result.getString("EMAIL")); 
                 employee.setReportsTo(result.getInt("REPORTS_TO")); 
                 employee.setRoleId(result.getInt("ROLE_ID")); 
                 employee.setDeptId(result.getInt("DEPT_ID")); 
            } else {
                  //System.out.println("Employee Object Not Found!");
                 System.out.println("Employee Object Not Found!");
            }
			conn.commit();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return employee;
    	
    }
    
    public Employee getEmployeeByLogin(String username, String password) {    	
    	Employee employee = null;
			try(Connection conn = ConnectionFactory.getConnection()) {
				conn.setAutoCommit(false);	
		        String sql = "SELECT * FROM EMPLOYEES WHERE USERNAME = ? AND PASSWORD = ?"; 
		        PreparedStatement ps = null;
		        ps = conn.prepareStatement(sql);
		        ps.setString(1, username);
		        ps.setString(2, password);
		        ResultSet result = ps.executeQuery();
		        if (result.next()) {
		        	 employee = new Employee();
		        	 employee.setEmployeeId(result.getInt("EMPLOYEE_ID"));
		             employee.setFirstName(result.getString("FIRST_NAME")); 
		             employee.setLastName(result.getString("LAST_NAME")); 
		             employee.setUsername(result.getString("USERNAME")); 
		             employee.setPassword(result.getString("PASSWORD")); 
		             employee.setEmail(result.getString("EMAIL")); 
		             employee.setReportsTo(result.getInt("REPORTS_TO")); 
		             employee.setRoleId(result.getInt("ROLE_ID")); 
		             employee.setDeptId(result.getInt("DEPT_ID")); 
		        } else {
		              //System.out.println("Employee Object Not Found!");
		             System.out.println("Employee Object Not Found!");
		        }
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return employee;
    }


    /**
     * ListEmployees-method. This will read all contents from database table and
     * build a List containing valueObjects. Please note, that this method
     * will consume huge amounts of resources if table has lot's of rows. 
     * This should only be used when target tables have only small amounts
     * of data.
     */
    public ArrayList<Employee> listEmployees() {

          ArrayList<Employee> searchResults = new ArrayList<>();
          ResultSet result = null;

          try (Connection conn = ConnectionFactory.getConnection()) {
        	  conn.setAutoCommit(false);
        	  String sql = "SELECT * FROM EMPLOYEES ORDER BY EMPLOYEE_ID ASC ";
        	  PreparedStatement ps = conn.prepareStatement(sql);
	          result = ps.executeQuery();
	
	          while (result.next()) {
	               Employee temp = new Employee();
	
	               temp.setEmployeeId(result.getInt("EMPLOYEE_ID")); 
	               temp.setFirstName(result.getString("FIRST_NAME")); 
	               temp.setLastName(result.getString("LAST_NAME")); 
	               temp.setUsername(result.getString("USERNAME")); 
	               temp.setPassword(result.getString("PASSWORD")); 
	               temp.setEmail(result.getString("EMAIL")); 
	               temp.setReportsTo(result.getInt("REPORTS_TO")); 
	               temp.setRoleId(result.getInt("ROLE_ID")); 
	               temp.setDeptId(result.getInt("DEPT_ID")); 
	
	               searchResults.add(temp);
	          }
	          conn.commit();

          } catch (SQLException e) {
        	  e.printStackTrace();
          }

          return searchResults;
    }

    /**
     * create-method. This will create new row in database according to supplied
     * valueObject contents. Make sure that values for all NOT NULL columns are
     * correctly specified. Also, if this table does not use automatic surrogate-keys
     * the primary-key must be specified. After INSERT command this method will 
     * read the generated primary-key back to valueObject if automatic surrogate-keys
     * were used. 
     *
     * @param valueObject  This parameter contains the class instance to be created.
     *                     If automatic surrogate-keys are not used the Primary-key 
     *                     field must be set for this to work properly.
     */
    public synchronized void create(Employee valueObject) {

          String sql = "";
          PreparedStatement stmt = null;
          ResultSet result = null;

          try (Connection conn = ConnectionFactory.getConnection()) {
			   conn.setAutoCommit(false);
			   sql = "INSERT INTO EMPLOYEES ( FIRST_NAME, LAST_NAME, USERNAME, "
			   + "PASSWORD, EMAIL, REPORTS_TO, "
			   + "ROLE_ID, DEPT_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
			   stmt = conn.prepareStatement(sql);
			
			   stmt.setString(1, valueObject.getFirstName()); 
			   stmt.setString(2, valueObject.getLastName()); 
			   stmt.setString(3, valueObject.getUsername()); 
			   stmt.setString(4, valueObject.getPassword()); 
			   stmt.setString(5, valueObject.getEmail()); 
			   stmt.setInt(6, valueObject.getReportsTo()); 
			   stmt.setInt(7, valueObject.getRoleId()); 
			   stmt.setInt(8, valueObject.getDeptId()); 
			
			   int rowcount = stmt.executeUpdate();
			   if (rowcount != 1) {
			        System.out.println("PrimaryKey Error when updating DB!");
			   }


	          /**
	          * The following query will read the automatically generated primary key
	          * back to valueObject. This must be done to make things consistent for 
	          * upper layer processing logic.
	          */
	          sql = "SELECT EMPLOYEE_SEQ.currval FROM dual";
		   
              stmt = conn.prepareStatement(sql);
              result = stmt.executeQuery();

              if (result.next()) {

                   valueObject.setEmployeeId(result.getInt(1));

              } else {
                   System.out.println("Unable to find primary-key for created object!");
              }
		   
			  conn.commit();
          } catch (SQLException e) {
        	  e.printStackTrace();
          }
    }
//

    /**
     * save-method. This method will save the current state of valueObject to database.
     * Save can not be used to create new instances in database, so upper layer must
     * make sure that the primary-key is correctly specified. Primary-key will indicate
     * which instance is going to be updated in database. If save can not find matching 
     * row, NotFoundException will be thrown.
     *
     * @param conn         This method requires working database connection.
     * @param valueObject  This parameter contains the class instance to be saved.
     *                     Primary-key field must be set for this to work properly.
     */
    public void save(Employee valueObject) {

          String sql = "UPDATE EMPLOYEES SET FIRST_NAME = ?, LAST_NAME = ?, USERNAME = ?, "
               + "PASSWORD = ?, EMAIL = ?, REPORTS_TO = ?, "
               + "ROLE_ID = ?, DEPT_ID = ? WHERE (EMPLOYEE_ID = ? ) ";
          PreparedStatement stmt = null;

          try (Connection conn = ConnectionFactory.getConnection()){
        	  conn.setAutoCommit(false);
              stmt = conn.prepareStatement(sql);
              stmt.setString(1, valueObject.getFirstName()); 
              stmt.setString(2, valueObject.getLastName()); 
              stmt.setString(3, valueObject.getUsername()); 
              stmt.setString(4, valueObject.getPassword()); 
              stmt.setString(5, valueObject.getEmail()); 
              stmt.setInt(6, valueObject.getReportsTo()); 
              stmt.setInt(7, valueObject.getRoleId()); 
              stmt.setInt(8, valueObject.getDeptId()); 

              stmt.setInt(9, valueObject.getEmployeeId()); 

              int rowcount = stmt.executeUpdate();
              if (rowcount == 0) {
                   System.out.println("Object could not be saved! (PrimaryKey not found)");
              }
              if (rowcount > 1) {
                   System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
              }
              
              conn.commit();
          } catch (SQLException e) {
        	  e.printStackTrace();
          }
    }


    /**
     * delete-method. This method will remove the information from database as identified by
     * by primary-key in supplied valueObject. Once valueObject has been deleted it can not 
     * be restored by calling save. Restoring can only be done using create method but if 
     * database is using automatic surrogate-keys, the resulting object will have different 
     * primary-key than what it was in the deleted object. If delete can not find matching row,
     * NotFoundException will be thrown.
     *
     * @param valueObject  This parameter contains the class instance to be deleted.
     *                     Primary-key field must be set for this to work properly.
     */
    public void delete(Employee valueObject) {

          String sql = "DELETE FROM EMPLOYEES WHERE (EMPLOYEE_ID = ? ) ";
          PreparedStatement stmt = null;

          try (Connection conn = ConnectionFactory.getConnection()){
        	  conn.setAutoCommit(false);
        	  
              stmt = conn.prepareStatement(sql);
              stmt.setInt(1, valueObject.getEmployeeId()); 

              int rowcount = stmt.executeUpdate();
              if (rowcount == 0) {
                   System.out.println("Object could not be deleted (PrimaryKey not found)");
              }
              if (rowcount > 1) {
                   System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
              }
              
              conn.commit();
          } catch (SQLException e) {
        	  e.printStackTrace();
          }
    }


    /**
     * deleteAll-method. This method will remove all information from the table that matches
     * this Dao and ValueObject couple. This should be the most efficient way to clear table.
     * Once deleteAll has been called, no valueObject that has been created before can be 
     * restored by calling save. Restoring can only be done using create method but if database 
     * is using automatic surrogate-keys, the resulting object will have different primary-key 
     * than what it was in the deleted object. (Note, the implementation of this method should
     * be different with different DB backends.)
     */
    public void deleteAll() {

          String sql = "DELETE FROM EMPLOYEES";
          PreparedStatement stmt = null;

          try (Connection conn = ConnectionFactory.getConnection()){
        	  conn.setAutoCommit(false);
        	  
              stmt = conn.prepareStatement(sql);
              int rowcount = stmt.executeUpdate();
              if (rowcount > 0) {
            	  System.out.println("DELETED FROM EMPLOYEES");
              }
              
              conn.commit();
          } catch (SQLException e) {
        	  e.printStackTrace();
          }
    }


    /**
     * coutAll-method. This method will return the number of all rows from table that matches
     * this Dao. The implementation will simply execute "select count(primarykey) from table".
     * If table is empty, the return value is 0. This method should be used before calling
     * loadAll, to make sure table has not too many rows.
     */
    public int countAll() {

          String sql = "SELECT count(*) FROM EMPLOYEES";
          PreparedStatement stmt = null;
          ResultSet result = null;
          int allRows = 0;

          try (Connection conn = ConnectionFactory.getConnection()){
        	  conn.setAutoCommit(false);
        	  
              stmt = conn.prepareStatement(sql);
              result = stmt.executeQuery();

              if (result.next())
                  allRows = result.getInt(1);
              conn.commit();
          } catch (SQLException e) {
        	  e.printStackTrace();
          }
          return allRows;
    }
}