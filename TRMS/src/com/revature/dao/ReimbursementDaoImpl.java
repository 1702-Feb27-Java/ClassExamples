package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.pojo.Reimbursement;
import com.revature.sql.ConnectionFactory;


 /**
  * Reimbursement Data Access Object (DAO).
  * This class contains all database handling that is needed to 
  * permanently store and retrieve Reimbursement object instances. 
  */
public class ReimbursementDaoImpl {

    /**
     * getObject-method. This will create and load valueObject contents from database 
     * using given Primary-Key as identifier. This method is just a convenience method 
     * for the real load-method which accepts the valueObject as a parameter. Returned
     * valueObject will be created using the createValueObject() method.
     */
    public Reimbursement getReimbursement(int reimbId) {

        String sql = "SELECT * FROM REIMBS WHERE (REIMB_ID = ? ) "; 
        PreparedStatement stmt = null;
        ResultSet result = null;
		Reimbursement valueObject = new Reimbursement();
        
    	try (Connection conn = ConnectionFactory.getConnection()){
    		conn.setAutoCommit(false);
			valueObject.setReimbId(reimbId);
	        stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, valueObject.getReimbId()); 
	        
	        result = stmt.executeQuery();

            if (result.next()) {
	            valueObject.setReimbId(result.getInt("REIMB_ID")); 
	            valueObject.setLocation(result.getString("LOCATION")); 
	            valueObject.setCost(result.getDouble("COST")); 
	            valueObject.setEventTitleId(result.getInt("EVENT_TITLE_ID")); 
	            valueObject.setEventDate(result.getDate("EVENT_DATE")); 
	            valueObject.setEventDesc(result.getString("EVENT_DESC")); 
	            valueObject.setWorkJust(result.getString("WORK_JUST")); 
	            valueObject.setGradeId(result.getInt("GRADE_ID")); 
	            valueObject.setStatusId(result.getInt("STATUS_ID")); 
            }
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return valueObject;
    }


    /**
     * databaseQuery-method. This method is a helper method for internal use. It will execute
     * all database queries that will return multiple rows. The resultset will be converted
     * to the List of valueObjects. If no rows were found, an empty List will be returned.
     *
     * @param conn         This method requires working database connection.
     * @param stmt         This parameter contains the SQL statement to be excuted.
     */
    public ArrayList<Reimbursement> listReimbursements() {

          ArrayList<Reimbursement> searchResults = new ArrayList<>();
          ResultSet result = null;
          PreparedStatement stmt = null;
          Reimbursement temp = new Reimbursement();

          try (Connection conn = ConnectionFactory.getConnection()){
        	  conn.setAutoCommit(false);
        	  
        	  String sql = "SELECT * FROM REIMBS ORDER BY REIMB_ID ASC";
        	  stmt = conn.prepareStatement(sql);
              result = stmt.executeQuery();

              while (result.next()) {

                   temp.setReimbId(result.getInt("REIMB_ID")); 
                   temp.setLocation(result.getString("LOCATION")); 
                   temp.setCost(result.getDouble("COST")); 
                   temp.setEventTitleId(result.getInt("EVENT_TITLE_ID")); 
                   temp.setEventDate(result.getDate("EVENT_DATE")); 
                   temp.setEventDesc(result.getString("EVENT_DESC")); 
                   temp.setWorkJust(result.getString("WORK_JUST")); 
                   temp.setGradeId(result.getInt("GRADE_ID")); 
                   temp.setStatusId(result.getInt("STATUS_ID")); 

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
     * @param conn         This method requires working database connection.
     * @param valueObject  This parameter contains the class instance to be created.
     *                     If automatic surrogate-keys are not used the Primary-key 
     *                     field must be set for this to work properly.
     */
    public synchronized void create(Reimbursement valueObject) {

          String sql = "";
          PreparedStatement stmt = null;
          ResultSet result = null;

          try (Connection conn = ConnectionFactory.getConnection()){
        	  conn.setAutoCommit(false);
               sql = "INSERT INTO REIMBS ( LOCATION, COST, EVENT_TITLE_ID, "
               + "EVENT_DATE, EVENT_DESC, WORK_JUST, "
               + "GRADE_ID, STATUS_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
               stmt = conn.prepareStatement(sql);

               stmt.setString(1, valueObject.getLocation()); 
               stmt.setDouble(2, valueObject.getCost()); 
               stmt.setInt(3, valueObject.getEventTitleId()); 
               stmt.setDate(4, valueObject.getEventDate()); 
               stmt.setString(5, valueObject.getEventDesc()); 
               stmt.setString(6, valueObject.getWorkJust()); 
               stmt.setInt(7, valueObject.getGradeId()); 
               stmt.setInt(8, valueObject.getStatusId()); 

               int rowcount = stmt.executeUpdate();
               if (rowcount != 1) {
                    //System.out.println("PrimaryKey Error when updating DB!");
                    throw new SQLException("PrimaryKey Error when updating DB!");
               }

               /**
                * The following query will read the automatically generated primary key
                * back to valueObject. This must be done to make things consistent for 
                * upper layer processing logic.
                */
                sql = "SELECT REIMB_SEQ.currval FROM dual";
			   
		       stmt = conn.prepareStatement(sql);
		       result = stmt.executeQuery();
		
		       if (result.next()) {
		
		            valueObject.setReimbId(result.getInt(1));
		
		       } else {
		            System.out.println("Unable to find primary-key for created object!");
		       }
		   
		       conn.commit();
		  } catch (SQLException e) {
			  e.printStackTrace();
		  }
    }


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
    public void save(Reimbursement valueObject) 
          {

          String sql = "UPDATE REIMBS SET LOCATION = ?, COST = ?, EVENT_TITLE_ID = ?, "
               + "EVENT_DATE = ?, EVENT_DESC = ?, WORK_JUST = ?, "
               + "GRADE_ID = ?, STATUS_ID = ? WHERE (REIMB_ID = ? ) ";
          PreparedStatement stmt = null;

          try (Connection conn = ConnectionFactory.getConnection()){
        	  conn.setAutoCommit(false);
              stmt = conn.prepareStatement(sql);
              stmt.setString(1, valueObject.getLocation()); 
              stmt.setDouble(2, valueObject.getCost()); 
              stmt.setInt(3, valueObject.getEventTitleId()); 
              stmt.setDate(4, valueObject.getEventDate()); 
              stmt.setString(5, valueObject.getEventDesc()); 
              stmt.setString(6, valueObject.getWorkJust()); 
              stmt.setInt(7, valueObject.getGradeId()); 
              stmt.setInt(8, valueObject.getStatusId()); 

              stmt.setInt(9, valueObject.getReimbId()); 

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
     * @param conn         This method requires working database connection.
     * @param valueObject  This parameter contains the class instance to be deleted.
     *                     Primary-key field must be set for this to work properly.
     */
    public void delete(Reimbursement valueObject) 
          {

          String sql = "DELETE FROM REIMBS WHERE (REIMB_ID = ? ) ";
          PreparedStatement stmt = null;

          try (Connection conn = ConnectionFactory.getConnection()){
        	  conn.setAutoCommit(false);
              stmt = conn.prepareStatement(sql);
              stmt.setInt(1, valueObject.getReimbId()); 

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
     *
     * @param conn         This method requires working database connection.
     */
    public void deleteAll() {

          String sql = "DELETE FROM REIMBS";
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
     *
     * @param conn         This method requires working database connection.
     */
    public int countAll() {

          String sql = "SELECT count(*) FROM REIMBS";
          PreparedStatement stmt = null;
          ResultSet result = null;
          int allRows = 0;

          try (Connection conn = ConnectionFactory.getConnection()) {
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