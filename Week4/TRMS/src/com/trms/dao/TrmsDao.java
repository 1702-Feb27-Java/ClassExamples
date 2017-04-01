package com.trms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.trms.connector.TRMSConnector;
import com.trms.signupserv.*;

public class TrmsDao {

	public static String getEmail(int userID) {
		String email = "";
		try (Connection c = TRMSConnector.getConnection()) {
			String sql = "SELECT email from accounts where userid = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();

			email = rs.getString(1);

		} catch (SQLException e) {
			System.out.println("get email error " + e);
		}
		return email;
	}

	public static boolean userExists(String username) {
		boolean userTaken = false;
		try (Connection c = TRMSConnector.getConnection()) {
			String sql = "SELECT USERNAME FROM ACCOUNTS WHERE USERNAME = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				userTaken = true;
			}

		} catch (SQLException e) {
			System.out.println("user exists error ");
			e.printStackTrace();
		}
		return userTaken;
	}

	public static boolean passMatch(String username, String password) {
		boolean passMatches = false;
		try (Connection c = TRMSConnector.getConnection()) {
			String sql = "SELECT PASS FROM ACCOUNTS WHERE USERNAME = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			String realPass = "";
			while (rs.next()) {
				realPass = rs.getString(1);
			}

			if (realPass.equals(password)) {
				passMatches = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("password match error ");
		}
		System.out.println(passMatches);
		return passMatches;
	}

	public static void createAccount(String username, String pass, String email, int empID) {

		try (Connection c = TRMSConnector.getConnection()) {

			String sql = "INSERT INTO ACCOUNTS(empid, username, pass, availfunds, email) VALUES (?,?,?,?,?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, empID);
			ps.setString(2, username);
			ps.setString(3, pass);
			ps.setInt(4, 1000);
			ps.setString(5, email);

			ps.executeQuery();

			System.out.println("ACCOUNT UPDATED");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("CREATE ACCOUNT ERROR. ");
		}

	}

	public static boolean isEmployee(int empID) {
		boolean empExists = false;
		try (Connection c = TRMSConnector.getConnection()) {
			String sql = "SELECT empID FROM EMPLOYEES WHERE empID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, empID);

			ResultSet rs = ps.executeQuery();

			empExists = rs.next();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("employee check ");
			e.printStackTrace();
		}

		return empExists;
	}

	/*
	 * public static void newRequest(int userid, String courseType, int
	 * reqAmount, int formid) { try (Connection c =
	 * TRMSConnector.getConnection()) { String sql =
	 * "INSERT INTO REQUESTS(USERID, COURSETYPE, REQAMOUNT, FORMID) VALUES(?,?,?,?)"
	 * ; PreparedStatement ps = c.prepareStatement(sql); ps.setInt(1, userid);
	 * ps.setString(2, courseType); ps.setInt(3, reqAmount); ps.setInt(4,
	 * formid);
	 * 
	 * ps.executeQuery();
	 * 
	 * System.out.println("REQUEST MADE ");
	 * 
	 * } catch (SQLException e) { e.printStackTrace();
	 * System.out.println("new request error "); } }
	 */

	/*
	 * public static void newForm(String location, String description, double
	 * cost, String gradeFormat, String typeOfEvent, String workRelation) { try
	 * (Connection c = TRMSConnector.getConnection()) { String sql =
	 * "INSERT INTO FORMS(LOCA, DESCRIPT, COST_, GRADFORM, TYPEOFEV, WORKREL) VALUES(?,?,?,?)"
	 * ; PreparedStatement ps = c.prepareStatement(sql); ps.setString(1,
	 * location); ps.setString(2, description); ps.setDouble(3, cost);
	 * ps.setString(4, gradeFormat); ps.setString(5, typeOfEvent);
	 * ps.setString(6, workRelation);
	 * 
	 * ps.executeQuery();
	 * 
	 * System.out.println("form MADE ");
	 * 
	 * } catch (SQLException e) { e.printStackTrace();
	 * System.out.println("new form error "); } }
	 */

	public static int getDepHead(int depNo) {
		int empid = 0;
		try (Connection c = TRMSConnector.getConnection()) {
			String sql = "SELECT DEPHEAD from deparment where depno = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, depNo);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				empid = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("get dep head error ");
		}

		return empid;
	}

	public static boolean emailExists(String email) {

		boolean emailTaken = false;

		try (Connection c = TRMSConnector.getConnection()) {

			String sql = "SELECT email from Accounts where email = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, email);
			String storedEmail = "";

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				storedEmail = rs.getString(1);
			}

			if (storedEmail.equals(email)) {
				emailTaken = true;
			}

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("email exists error");
		}

		return emailTaken;
	}

	public static boolean empExists(int empid) {
		boolean empTaken = false;

		try (Connection c = TRMSConnector.getConnection()) {

			String sql = "SELECT EMPID FROM ACCOUNTS WHERE EMPID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, empid);

			ResultSet rs = ps.executeQuery();
			int storedEmpid = 0;

			while (rs.next()) {
				storedEmpid = rs.getInt(1);
			}

			if (storedEmpid == empid) {
				empTaken = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("emp exists error");
		}
		return empTaken;
	}

	public static boolean isEmp(int empid) {
		boolean empExists = false;

		try (Connection c = TRMSConnector.getConnection()) {

			String sql = "SELECT EMPID FROM Employee WHERE EMPID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, empid);

			ResultSet rs = ps.executeQuery();
			int storedEmpid = 0;

			while (rs.next()) {
				storedEmpid = rs.getInt(1);
			}

			if (storedEmpid == empid) {
				empExists = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("emp exists error");
		}
		return empExists;
	}

	public static void requestReimbursement(int coursetype, String startdate, String enddate, double cost,
			double requestamount, String location, int gradeformat, String description, String workjust,
			String currentdate, String currenttime, String username, int status) {

		int courseInfoID = 0;
		try (Connection c = TRMSConnector.getConnection()) {
			c.setAutoCommit(false);

			createCourseInfo(coursetype, workjust, cost, gradeformat, location, startdate, enddate);

			// need to get the newly created course info id
			String courseSQL = "SELECT COURSEINFOID FROM COURSEINFO WHERE ROWID = (SELECT MAX(ROWID) FROM COURSEInfo)";
			PreparedStatement ps = c.prepareStatement(courseSQL);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				courseInfoID = rs.getInt(1);
			}

			// now creating the new form

			createForms(currentdate, currenttime, description);

			// getting id of last created form

			String formSQL = "SELECT formid FROM forms WHERE ROWID = (SELECT MAX(ROWID) FROM forms)";
			ps = c.prepareStatement(formSQL);
			rs = ps.executeQuery();
			int formID = 0;

			while (rs.next()) {
				formID = rs.getInt(1);
			}

			// need to grab id from current session's username

			int userId = getUserId(username);

			// now we can create the new request row
			String sql = "INSERT INTO REQUESTS(userid, status, reqamount, formid, courseinfoid) VALUES(?,?,?,?,?)";
			ps = c.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, status);
			ps.setDouble(3, requestamount);
			ps.setInt(4, formID);
			ps.setInt(5, courseInfoID);
			ps.executeQuery();

			c.commit();
			c.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println();
		}

	}

	public static void createCourseInfo(int eventid, String workrel, double cost, int gradeformat, String location,
			String startdate, String enddate) {

		try (Connection c = TRMSConnector.getConnection()) {

			System.out.println("TRYING TO CREATE NEW COURSEINFO");

			String sql = "INSERT INTO COURSEINFO(EVENTID, WORKRELATED, COST, GRADEFORMATID, LOCATION, STARTDATE, ENDDATE) VALUES(?,?,?,?,?,?,?)";
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setInt(1, eventid);
			ps.setString(2, workrel);
			ps.setDouble(3, cost);
			ps.setInt(4, gradeformat);
			ps.setString(5, location);
			ps.setString(6, startdate);
			ps.setString(7, enddate);
			ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("courseinfo create error ");
		}

		System.out.println("NEW COURSEINFO CREATED!!");

	}

	// USES GETUSERID TO GET USERID WITH USERNAME AND GET EMP ID TO GET EMPID
	// WITH USERID
	public static boolean checkIfSuper(String username) {
		boolean isSuper = false;
		try (Connection c = TRMSConnector.getConnection()) {
			int userID = getUserId(username);
			int empId = getEmpidWithUserid(userID);

			String sql = "SELECT DEPHEAD FROM DEPARTMENT WHERE DEPHEAD = (SELECT REPORTSTO FROM EMPLOYEE WHERE EMPID = ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, empId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				isSuper = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("check if super error");
		}
		return isSuper;
	}

	public static boolean checkIfDepHead(String username) {
		boolean isDepHead = false;
		try (Connection c = TRMSConnector.getConnection()) {
			int userID = getUserId(username);
			int empId = getEmpidWithUserid(userID);

			String sql = "SELECT DEPHEAD FROM DEPARTMENT where dephead = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, empId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				isDepHead = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("check if super error");
		}
		return isDepHead;
	}

	public static boolean checkIfBenCo(String username) {
		boolean isBenCo = false;
		try (Connection c = TRMSConnector.getConnection()) {
			int userID = getUserId(username);
			int empId = getEmpidWithUserid(userID);
			String sql = "SELECT DEPNO FROM EMPLOYEE WHERE EMPID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, empId);
			ResultSet rs = ps.executeQuery();
			int depno = 0;

			while (rs.next()) {
				depno = rs.getInt(1);
			}

			if (depno == 8) {
				isBenCo = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("check if benco");
		}

		return isBenCo;

	}

	public static void createForms(String date, String time, String description) {

		try (Connection c = TRMSConnector.getConnection()) {

			System.out.println("TRYING TO CREATE NEW FORM!!!!!!!!!!");

			String sql = "INSERT INTO FORMS(DATE_, TIME_, DESCRIPT) VALUES(?,?,?)";
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setString(1, date);
			ps.setString(2, time);
			ps.setString(3, description);
			ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Form create error ");
		}
		System.out.println("FORM CREATION SUCESS!!");

	}

	public static int getUserId(String username) {
		int userId = 0;
		try (Connection c = TRMSConnector.getConnection()) {

			String sql = "SELECT USERID FROM ACCOUNTS WHERE USERNAME = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				userId = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("get user id error");
		}
		return userId;
	}

	public static int getEmpidWithUserid(int userId) {
		int empId = 0;
		try (Connection c = TRMSConnector.getConnection()) {

			String sql = "SELECT EMPID FROM ACCOUNTS WHERE USERID = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				empId = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getEmpidWithUserid error");
		}
		return empId;
	}

	public static double getAvailFunds(String username) {
		double funds = 0;
		try (Connection c = TRMSConnector.getConnection()) {
			String sql = "SELECT AVAILFUNDS FROM ACCOUNTS WHERE USERNAME = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				funds = rs.getDouble(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("get avail funds error");
		}
		return funds;
	}

	public static void updateFunds(String username, double amount) {
		try (Connection c = TRMSConnector.getConnection()) {
			double funds = getAvailFunds(username);
			funds -= amount;
			String sql = "UPDATE ACCOUNTS SET AVAILFUNDS = ? WHERE USERNAME = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setDouble(1, funds);
			ps.setString(2, username);
			ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("update funds error");
		}
	}

	// returns a list of all user'S pending requests

	public static ArrayList getListOfPendingId(String username) {
		ArrayList<Integer> pending = new ArrayList<Integer>();
		try (Connection c = TRMSConnector.getConnection()) {
			int userid = getUserId(username);
			String sql = "SELECT REQID from REQUESTS WHERE USERID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				pending.add(rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("get list of pending error");
		}
		return pending;
	}

	public static void markUrgent(String username) {
		try (Connection c = TRMSConnector.getConnection()) {

			int userid = getUserId(username);
			String sql = "UPDATE REQUESTS SET URGENT = 2 WHERE USERID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, userid);
			ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("mark urgent error");
		}
	}

	// HERE STARTING ARE METHODS TO RETURN DISPLAY INFO BASED ON REQID
	public static String getDateSubmitted(int reqid) {

		String dateSubmitted = "";
		try (Connection c = TRMSConnector.getConnection()) {
			String sql = "select date_ from forms inner join requests on requests.formid = forms.formid where requests.reqid = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, reqid);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				dateSubmitted = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("get date submit error ");
		}
		return dateSubmitted;

	}

	public static String getCourseType(int reqid) {
		String courseType = "";
		try (Connection c = TRMSConnector.getConnection()) {

			String sql = " select e.event from event e " + "inner join courseinfo c on e.eventid = c.eventid "
					+ "inner join requests r on r.courseinfoid = c.courseinfoid " + "where r.reqid = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, reqid);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				courseType = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("get course type error");
		}
		return courseType;
	}

	public static ArrayList getReqStatAward(int reqid) {
		ArrayList<Object> reqStatAward = new ArrayList<>();
		try (Connection c = TRMSConnector.getConnection()) {
			String sql = "SELECT REQAMOUNT, STATUS, AWARD FROM REQUESTS WHERE REQID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, reqid);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				reqStatAward.add(rs.getDouble(1));
				reqStatAward.add(rs.getInt(2));
				reqStatAward.add(rs.getDouble(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("get req and awar error");
		}
		return reqStatAward;
	}

	public static int getUrgent(int reqid) {
		int urgent = 0;
		try (Connection c = TRMSConnector.getConnection()) {

			String sql = "SELECT URGENT FROM REQUESTS WHERE REQID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, reqid);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				urgent = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("get urgent error");
		}
		return urgent;
	}

	// getting list of users pending requests for DEP HEAD

	public static ArrayList<Integer> getListOfPendingDepHead(String username) {
		ArrayList<Integer> pendingDepHead = new ArrayList<Integer>();
		try (Connection c = TRMSConnector.getConnection()) {

			String sql = "SELECT REQUESTS.REQID " + "FROM REQUESTS " + "INNER JOIN ACCOUNTS "
					+ "ON REQUESTS.USERID = ACCOUNTS.USERID " + "INNER JOIN EMPLOYEE "
					+ "ON ACCOUNTS.EMPID = EMPLOYEE.EMPID "
					+ "WHERE REQUESTS.STATUS = 2 AND EMPLOYEE.DEPNO = (SELECT EMPLOYEE.DEPNO " + "FROM EMPLOYEE "
					+ "INNER JOIN ACCOUNTS " + "ON EMPLOYEE.EMPID = ACCOUNTS.EMPID " + "WHERE ACCOUNTS.USERNAME = ?)";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				pendingDepHead.add(rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("get pending dep head error ");
		}

		return pendingDepHead;
	}

	// getting list of pending users for SUPERVISOR

	public static ArrayList<Integer> getListOfPendingSuper(String username) {
		ArrayList<Integer> pendingSuper = new ArrayList<Integer>();
		try (Connection c = TRMSConnector.getConnection()) {

			String sql = "SELECT REQUESTS.REQID FROM REQUESTS " + "INNER JOIN ACCOUNTS "
					+ "ON REQUESTS.USERID = ACCOUNTS.USERID " + "INNER JOIN EMPLOYEE "
					+ "ON ACCOUNTS.EMPID = EMPLOYEE.EMPID "
					+ "WHERE REQUESTS.STATUS = 1 AND EMPLOYEE.REPORTSTO = (SELECT EMPLOYEE.EMPID " + "FROM EMPLOYEE "
					+ "INNER JOIN ACCOUNTS " + "ON EMPLOYEE.EMPID = ACCOUNTS.EMPID " + "WHERE ACCOUNTS.USERNAME = ?)";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				pendingSuper.add(rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("get pending super error ");
		}

		return pendingSuper;
	}

	public static ArrayList<Integer> getListOfPendingBenCo(String username) {
		ArrayList<Integer> pendingBenCo = new ArrayList<Integer>();
		try (Connection c = TRMSConnector.getConnection()) {

			int userID = getUserId(username);
			int empId = getEmpidWithUserid(userID);

			String sql = "SELECT REQUESTS.REQID FROM REQUESTS " + "INNER JOIN ACCOUNTS "
					+ "ON REQUESTS.USERID = ACCOUNTS.USERID " + "INNER JOIN EMPLOYEE "
					+ "ON ACCOUNTS.EMPID = EMPLOYEE.EMPID " + "WHERE REQUESTS.STATUS = 3 AND EMPLOYEE.EMPID != ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, empId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				pendingBenCo.add(rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("get pending benco error");
		}

		return pendingBenCo;
	}

	public static void updateStatus(int reqid, int statusUpdateTo) {
		try (Connection c = TRMSConnector.getConnection()) {
			String sql = "UPDATE REQUESTS SET STATUS = ? WHERE REQID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, statusUpdateTo);
			ps.setInt(2, reqid);
			ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("update status error");
		}
	}

	public static int getStatus(int reqid) {

		int status = 0;
		try (Connection c = TRMSConnector.getConnection()) {
			String sql = "SELECT STATUS FROM REQUESTS WHERE REQID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, reqid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				status = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("update status error");
		}

		return status;

	}

	public static void requestAdditionalInfo(int reqid) {
		try (Connection c = TRMSConnector.getConnection()) {
			String sql = "UPDATE REQUESTS SET ADDINFO = 2 WHERE REQID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, reqid);
			ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("get add info error");
		}
	}

	public static void additionalInfoMessage(String username, int reqid, String message) {
		try (Connection c = TRMSConnector.getConnection()) {

			int userID = getUserId(username);
			int empid = getEmpidWithUserid(userID);
			String sql = "INSERT INTO ADDINFOMESSAGES(FROMEMPID, REQID, MESSAGE) VALUES(?,?,?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, empid);
			ps.setInt(2, reqid);
			ps.setString(3, message);
			ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("additional info messsage");
		}
	}

	public static int getAddInfoStatus(int reqid) {
		int addinfo = 0;
		try (Connection c = TRMSConnector.getConnection()) {
			String sql = "SELECT ADDINFO from REQUESTS WHERE REQID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, reqid);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				addinfo = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("get add info status error ");
		}
		return addinfo;
	}

	public static String getAddInfoMessage(int reqid) {
		String message = "";
		try (Connection c = TRMSConnector.getConnection()) {

			String sql = "SELECT MESSAGE FROM ADDINFOMESSAGES WHERE REQID = ? and resolved = 1";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, reqid);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				message = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("get add in fo mess error");
		}
		return message;
	}

	public static int getAddInfoFrom(int reqid) {
		int empid = 0;
		try (Connection c = TRMSConnector.getConnection()) {

			String sql = "SELECT fromempid FROM ADDINFOMESSAGES WHERE REQID = ? and resolved = 1";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, reqid);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				empid = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("get add in fo mess error");
		}

		return empid;
	}
	
	public static void setAddInfoResolvedToTrue(int reqid){
		try (Connection c = TRMSConnector.getConnection()) {

			String sql = "update ADDINFOMESSAGES set resolved = 2 WHERE REQID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, reqid);
			ResultSet rs = ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("set add info to resolved");
		}
	}
	public static void setRequestsAddInfoToFalse(int reqid){
		try (Connection c = TRMSConnector.getConnection()) {

			String sql = "update requests set addinfo = 1 WHERE REQID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, reqid);
			ResultSet rs = ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("set addinfo in request to false error");
		}
	}
	
	public static String getUsername(int empid){
		String username = "";
		try (Connection c = TRMSConnector.getConnection()) {

			String sql = "SELECT username FROM accounts WHERE empid = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, empid);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				username = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("get add in fo mess error");
		}
		
		return username;
		
	}
	
	public static void newAttachment(String attachLocation, int reqid){
		try(Connection c = TRMSConnector.getConnection()){
			String sql = "INSERT INTO ATTACHMENTS(ATTACHLOCATION, REQID) VALUES(?,?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, attachLocation);
			ps.setInt(2, reqid);
			ps.executeQuery();
			
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println(" new attachment error ");
		}
	}

	/*
	 * public static ArrayList getSignUpArray(){
	 * 
	 * ArrayList<AccountClass> al = new ArrayList<AccountClass>();
	 * try(Connection c = TRMSConnector.getConnection()){
	 * 
	 * String sql = "SELECT EMPID, USERNAME, EMAIL FROM ACCOUNTS";
	 * PreparedStatement ps = c.prepareStatement(sql); ResultSet rs =
	 * ps.executeQuery();
	 * 
	 * while(rs.next()){ al.add(new AccountClass(rs.getString(2),
	 * rs.getString(3), rs.getInt(1))); }
	 * 
	 * 
	 * 
	 * }catch(SQLException e){ e.printStackTrace();
	 * System.out.println("sign up array error"); }
	 * 
	 * return al; }
	 */

}
