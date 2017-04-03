package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.revature.pojo.Grade;
import com.revature.sql.ConnectionFactory;

/**
 * Lookup Data Access Object (DAO).
 * This class contains all database handling that is needed to 
 * permanently store and retrieve lookup table values
 */

public class LookupDaoImpl {
	// dept, role, urgency, grade, event_title, app_levels tables expressed
	public String getRole(int roleId) {
		String role = null;
		try(Connection conn = ConnectionFactory.getConnection()) {
			conn.setAutoCommit(false);	
	        String sql = "SELECT ROLE FROM ROLE WHERE ROLE_ID = ?"; 
	        PreparedStatement ps = null;
	        ps = conn.prepareStatement(sql);
	        ps.setInt(1, roleId);
	        ResultSet result = ps.executeQuery();
	        if (result.next()) {
	        	 role = result.getString(1);
	        } else {
	             System.out.println("Role String Not Found!");
	        }
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return role;
	}
	
	public ArrayList<String> listRoles() {
		ArrayList<String> roles = null;
		try(Connection conn = ConnectionFactory.getConnection()) {
			conn.setAutoCommit(false);	
	        String sql = "SELECT ROLE FROM ROLE ORDER BY ROLE_ID ASC"; 
	        PreparedStatement ps = null;
	        ps = conn.prepareStatement(sql);
	        ResultSet result = ps.executeQuery();
	        if (result.next()) {
	        	 roles.add(result.getString(1));
	        } else {
	             System.out.println("Role String Not Found!");
	        }
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roles;
	}
	
	public String getDept(int deptId) {
		String dept = null;
		try(Connection conn = ConnectionFactory.getConnection()) {
			conn.setAutoCommit(false);	
	        String sql = "SELECT DEPT FROM DEPT WHERE DEPT_ID = ?"; 
	        PreparedStatement ps = null;
	        ps = conn.prepareStatement(sql);
	        ps.setInt(1, deptId);
	        ResultSet result = ps.executeQuery();
	        if (result.next()) {
	        	 dept = result.getString(1);
	        } else {
	             System.out.println("Dept String Not Found!");
	        }
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dept;
	}
	
	public ArrayList<String> listDepts() {
		ArrayList<String> depts = new ArrayList<>();
		try(Connection conn = ConnectionFactory.getConnection()) {
			conn.setAutoCommit(false);	
	        String sql = "SELECT DEPT FROM DEPT ORDER BY DEPT_ID ASC"; 
	        PreparedStatement ps = null;
	        ps = conn.prepareStatement(sql);
	        ResultSet result = ps.executeQuery();
	        while (result.next()) {
	        	 depts.add(result.getString(1));
	        }
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return depts;
	}
	
	public String getGrade(int gradeId) {
		String grade = null;
		try(Connection conn = ConnectionFactory.getConnection()) {
			conn.setAutoCommit(false);	
	        String sql = "SELECT GRADE_FORMAT FROM GRADE WHERE GRADE_ID = ?"; 
	        PreparedStatement ps = null;
	        ps = conn.prepareStatement(sql);
	        ps.setInt(1, gradeId);
	        ResultSet result = ps.executeQuery();
	        if (result.next()) {
	        	 grade = result.getString(1);
	        } else {
	             System.out.println("Grade String Not Found!");
	        }
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return grade;
	}
	
	public ArrayList<Grade> listGrades() {
		ArrayList<Grade> grades = null;
		try(Connection conn = ConnectionFactory.getConnection()) {
			conn.setAutoCommit(false);	
	        String sql = "SELECT * FROM GRADE ORDER BY GRADE_ID ASC"; 
	        PreparedStatement ps = null;
	        ps = conn.prepareStatement(sql);
	        ResultSet result = ps.executeQuery();
	        grades = new ArrayList<Grade>();
	        while (result.next()) {
	        	Grade temp = new Grade();
	        	temp.setGradeId(result.getInt(1));
	        	temp.setGradeFormat(result.getString(2));
	        	temp.setPassingGrade(result.getString(3));
	        	temp.setPresReq(result.getBoolean(4));
	        	grades.add(temp);
			conn.commit();
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return grades;
	}
	
	public synchronized int insertGrade(Grade g) {

		String sql = "";
		PreparedStatement stmt = null;
		ResultSet result = null;

		try (Connection conn = ConnectionFactory.getConnection()) {
			conn.setAutoCommit(false);
			sql = "INSERT INTO GRADE ( GRADE_FORMAT, PASSING_GRADE, PRES_REQ ) VALUES (?, ?, ?) ";
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, g.getGradeFormat());
			stmt.setString(2, g.getPassingGrade());
			stmt.setBoolean(3, g.isPresReq());

			int rowcount = stmt.executeUpdate();
			if (rowcount != 1) {
				System.out.println("PrimaryKey Error when updating DB!");
			}

			/**
			 * The following query will read the automatically generated primary
			 * key back to valueObject. This must be done to make things
			 * consistent for upper layer processing logic.
			 */
			sql = "SELECT GRADE_SEQ.currval FROM dual";

			stmt = conn.prepareStatement(sql);
			result = stmt.executeQuery();

			if (result.next()) {

				g.setGradeId(result.getInt(1));

			} else {
				System.out.println("Unable to find primary-key for created object!");
			}

			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return g.getGradeId();
	}

	public String getEventTitle(int eventTitleId) {
		String eventTitle = null;
		try (Connection conn = ConnectionFactory.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "SELECT EVENT_TITLE FROM EVENT_TITLE WHERE EVENT_TITLE_ID = ?";
			PreparedStatement ps = null;
			ps = conn.prepareStatement(sql);
			ps.setInt(1, eventTitleId);
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				eventTitle = result.getString(1);
			} else {
				System.out.println("Event Title String Not Found!");
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eventTitle;
	}

    public ArrayList<String> listEventTitles() {

        ArrayList<String> searchResults = new ArrayList<>();
        ResultSet result = null;

        try (Connection conn = ConnectionFactory.getConnection()) {
      	  conn.setAutoCommit(false);
      	  String sql = "SELECT EVENT_TITLE FROM EVENT_TITLE ORDER BY EVENT_TITLE_ID ASC ";
      	  PreparedStatement ps = conn.prepareStatement(sql);
	          result = ps.executeQuery();
	
	          while (result.next()) {
	        	  String temp = result.getString(1);
	              searchResults.add(temp);
	          }
	          conn.commit();

        } catch (SQLException e) {
      	  e.printStackTrace();
        }

        return searchResults;
    }
    
    public synchronized int insertEvent(String eventTitle) {
		String sql = "";
		PreparedStatement stmt = null;
		ResultSet result = null;
		int lastId = 0;

		try (Connection conn = ConnectionFactory.getConnection()) {
			conn.setAutoCommit(false);
			sql = "INSERT INTO EVENT_TITLE ( EVENT_TITLE ) VALUES (?) ";
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, eventTitle);

			int rowcount = stmt.executeUpdate();
			if (rowcount != 1) {
				System.out.println("PrimaryKey Error when updating DB!");
			}

			/**
			 * The following query will read the automatically generated primary
			 * key back to valueObject. This must be done to make things
			 * consistent for upper layer processing logic.
			 */
			sql = "SELECT EVENT_TITLE_SEQ.currval FROM dual";

			stmt = conn.prepareStatement(sql);
			result = stmt.executeQuery();

			if (result.next()) {

				lastId = result.getInt(1);

			} else {
				System.out.println("Unable to find primary-key for created object!");
			}

			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lastId;
    
	}
    // TODO: URGENCY
    // TODO: APP_LEVELS
    public HashMap<Integer, String> mapEventTitles() {

        HashMap<Integer, String> searchResultsMap = new HashMap<>();
        ResultSet result = null;

        try (Connection conn = ConnectionFactory.getConnection()) {
      	  conn.setAutoCommit(false);
      	  String sql = "SELECT EVENT_TITLE_ID, EVENT_TITLE FROM EVENT_TITLE ORDER BY EVENT_TITLE_ID ASC ";
      	  PreparedStatement ps = conn.prepareStatement(sql);
	          result = ps.executeQuery();
	
	          while (result.next()) {
	        	  int tempInt = result.getInt(1);
	        	  String tempString = result.getString(2);
	              searchResultsMap.put(tempInt, tempString);
	          }
	          conn.commit();

        } catch (SQLException e) {
      	  e.printStackTrace();
        }

        return searchResultsMap;
    }
    
    public HashMap<Integer, String> mapAppLevels() {

        HashMap<Integer, String> searchResultsMap = new HashMap<>();
        ResultSet result = null;

        try (Connection conn = ConnectionFactory.getConnection()) {
      	  conn.setAutoCommit(false);
      	  String sql = "SELECT APP_LEVEL, APP_LEVEL_TITLE FROM APP_LEVELS ORDER BY APP_LEVEL ASC ";
      	  PreparedStatement ps = conn.prepareStatement(sql);
	          result = ps.executeQuery();
	
	          while (result.next()) {
	        	  int tempInt = result.getInt(1);
	        	  String tempString = result.getString(2);
	              searchResultsMap.put(tempInt, tempString);
	          }
	          conn.commit();

        } catch (SQLException e) {
      	  e.printStackTrace();
        }

        return searchResultsMap;
    }
    
    public HashMap<Integer, String> mapDepts() {

        HashMap<Integer, String> searchResultsMap = new HashMap<>();
        ResultSet result = null;

        try (Connection conn = ConnectionFactory.getConnection()) {
      	  conn.setAutoCommit(false);
      	  String sql = "SELECT DEPT_ID, DEPT FROM DEPT ORDER BY DEPT_ID ASC ";
      	  PreparedStatement ps = conn.prepareStatement(sql);
	          result = ps.executeQuery();
	
	          while (result.next()) {
	        	  int tempInt = result.getInt(1);
	        	  String tempString = result.getString(2);
	              searchResultsMap.put(tempInt, tempString);
	          }
	          conn.commit();

        } catch (SQLException e) {
      	  e.printStackTrace();
        }

        return searchResultsMap;
    }
    
    public HashMap<Integer, String> mapRoles() {

        HashMap<Integer, String> searchResultsMap = new HashMap<>();
        ResultSet result = null;

        try (Connection conn = ConnectionFactory.getConnection()) {
      	  conn.setAutoCommit(false);
      	  String sql = "SELECT ROLE_ID, ROLE FROM ROLE ORDER BY ROLE_ID ASC ";
      	  PreparedStatement ps = conn.prepareStatement(sql);
	          result = ps.executeQuery();
	
	          while (result.next()) {
	        	  int tempInt = result.getInt(1);
	        	  String tempString = result.getString(2);
	              searchResultsMap.put(tempInt, tempString);
	          }
	          conn.commit();

        } catch (SQLException e) {
      	  e.printStackTrace();
        }

        return searchResultsMap;
    }
    
    public HashMap<Integer, String> mapUrgencyLevels() {

        HashMap<Integer, String> searchResultsMap = new HashMap<>();
        ResultSet result = null;

        try (Connection conn = ConnectionFactory.getConnection()) {
      	  conn.setAutoCommit(false);
      	  String sql = "SELECT URGENCY_ID, URGENCY_LEVEL FROM URGENCY ORDER BY URGENCY_ID ASC ";
      	  PreparedStatement ps = conn.prepareStatement(sql);
	          result = ps.executeQuery();
	
	          while (result.next()) {
	        	  int tempInt = result.getInt(1);
	        	  String tempString = result.getString(2);
	              searchResultsMap.put(tempInt, tempString);
	          }
	          conn.commit();

        } catch (SQLException e) {
      	  e.printStackTrace();
        }

        return searchResultsMap;
    }
    
    public String getUrgencyLevel(int uId) {

        String searchResult = null;
        ResultSet result = null;

        try (Connection conn = ConnectionFactory.getConnection()) {
      	  conn.setAutoCommit(false);
      	  String sql = "SELECT URGENCY_LEVEL FROM URGENCY WHERE URGENCY_ID = ? ";
      	  PreparedStatement ps = conn.prepareStatement(sql);
      	  ps.setInt(1, uId);
	          result = ps.executeQuery();
	
	          if (result.next()) {
	        	  searchResult = result.getString(1);
	          }
	          conn.commit();

        } catch (SQLException e) {
      	  e.printStackTrace();
        }

        return searchResult;
    }
    
	public String getAppLevelTitle(int appLevel) {
		// TODO Auto-generated method stub
		String appLevelTitle = null;
		ResultSet result = null;
        try (Connection conn = ConnectionFactory.getConnection()) {
        	  conn.setAutoCommit(false);
        	  String sql = "SELECT APP_LEVEL_TITLE FROM APP_LEVELS WHERE APP_LEVEL = ? ";
        	  PreparedStatement ps = conn.prepareStatement(sql);
        	  ps.setInt(1, appLevel);
  	          result = ps.executeQuery();
  	
  	          if (result.next()) {
  	        	  appLevelTitle = result.getString(1);
  	          }
  	          conn.commit();

          } catch (SQLException e) {
        	  e.printStackTrace();
          }
		return appLevelTitle;
	}
    
    public int countDepts() {

        String sql = "SELECT count(*) FROM DEPT";
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
    
    public int countRoles() {

        String sql = "SELECT count(*) FROM ROLE";
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
    
    public int countGrades() {

        String sql = "SELECT count(*) FROM GRADE";
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
    
    public int countEventTitles() {

        String sql = "SELECT count(*) FROM EVENT_TITLE";
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
