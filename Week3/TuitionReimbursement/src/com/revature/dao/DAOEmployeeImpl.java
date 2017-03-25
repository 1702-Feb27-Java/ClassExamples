package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.pojo.Message;
import com.revature.pojo.Reimbursement;
import com.revature.util.ConnectionUtil;

public class DAOEmployeeImpl implements DAOEmployee{
	
	@Override
	public ArrayList<String> loginEmployee(String un, String pw) {
		ArrayList<String> employee = new ArrayList<String>();
		int customerId = 0;
		String password = "";
			try(Connection connect = ConnectionUtil.getConnection();){
				connect.setAutoCommit(false);
				
				String sql = "CALL LOGINEMPLOYEE(?, ?, ?)";
				CallableStatement cs = connect.prepareCall(sql);
				
				cs.registerOutParameter(1, java.sql.Types.INTEGER);
				cs.setString(2, un);
				cs.registerOutParameter(3, java.sql.Types.VARCHAR);
			
				cs.execute();
				
				customerId = cs.getInt(1);
				password = cs.getString(3);
				
				connect.commit();	
				employee.add(Integer.toString(customerId));
				employee.add(password);
		
			}
			catch(SQLException e){
				e.printStackTrace(); 	
			}
		
		return employee;
	}

	@Override
	public boolean applyForReimbursement(int emp_id, String event, Date eventDate, String time, int location, Date formDate,
			String description, int cost, int gradingId, int typeOfEventId, int urgentId, int approvalStepId, Date approvalCutoff) {
		
		boolean applied = false;
			try(Connection connect = ConnectionUtil.getConnection();){
				connect.setAutoCommit(false);
				
				String sql = "CALL APPLYFORREIMBURSEMENT(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				CallableStatement cs = connect.prepareCall(sql);
				
				cs.setInt(1, emp_id);
				cs.setString(2, event);
				cs.setDate(3, eventDate);
				cs.setString(4, time);
				cs.setInt(5, location);
				cs.setDate(6, formDate);
				cs.setString(7, description);
				cs.setInt(8, cost);
				cs.setInt(9, gradingId);
				cs.setInt(10, typeOfEventId);
				cs.setInt(11, urgentId);
				cs.setInt(12, approvalStepId);
				cs.setDate(13, approvalCutoff);
			
				cs.executeUpdate();
				
				connect.commit();	
				
				applied = true;
		
			}
			catch(SQLException e){
				e.printStackTrace(); 	
			}
		
		return applied;
	}

	@Override
	public ArrayList<String> getListOfLocations() {
		ArrayList<String> locations = new ArrayList<String>();
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);

			String sql = "SELECT location FROM Location";
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String location = rs.getString(1);
				
				locations.add(location);
			}
	
		}
		catch(SQLException e){
			e.printStackTrace(); 	
		}
		if(locations.isEmpty()){
			locations.add("No Previous Locations");
		}
		return locations;
	}

	
	@Override
	public ArrayList<String> getListOfGradingTypes() {
		
		ArrayList<String> gradingTypes = new ArrayList<String>();
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);

			String sql = "SELECT grading FROM Grading";
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String gradingType = rs.getString(1);
				
				gradingTypes.add(gradingType);
			}
	
		}
		catch(SQLException e){
			e.printStackTrace(); 	
		}
		if(gradingTypes.isEmpty()){
			gradingTypes.add("No previous grading types.");
		}
		return gradingTypes;
	}

	public ArrayList<String> getListOfEventTypes(){
		ArrayList<String> eventTypes = new ArrayList<String>();
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
	
			String sql = "SELECT type_of_event FROM TYPE_OF_EVENT";
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String eventType = rs.getString(1);
				
				eventTypes.add(eventType);
			}
	
		}
		catch(SQLException e){
			e.printStackTrace(); 	
		}
		if(eventTypes.isEmpty()){
			eventTypes.add("No previous event types.");
		}
		return eventTypes;
	}

	public int getLocationId(String location){
		int locationId = 0;
		
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
	
			String sql = "SELECT LOCATION_ID FROM Location WHERE location = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setString(1, location);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				locationId = rs.getInt(1);
			}
	
		}
		catch(SQLException e){
			e.printStackTrace(); 	
		}
		return locationId;
	}

	public int addLocation(String location){
		int locationId = 0;
		
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
	
			String sql = "CALL ADDLOCATION(?, ?)";
			CallableStatement cs = connect.prepareCall(sql);
			
			cs.setString(1, location);
			cs.registerOutParameter(2, java.sql.Types.INTEGER);
			
			cs.execute();
			locationId = cs.getInt(2);
		}
		catch(SQLException e){
			e.printStackTrace(); 	
		}
		return locationId;	
	}
	
	public int getGradingId(String grading){
		int gradingId = 0;
		
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
	
			String sql = "SELECT GRADING_ID FROM GRADING WHERE Grading = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setString(1, grading);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				gradingId = rs.getInt(1);
			}
	
		}
		catch(SQLException e){
			e.printStackTrace(); 	
		}
		return gradingId;
	}

	public int addGrading(String grading, String passingGrade){
		int gradingId = 0;
		
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
	
			String sql = "CALL ADDGRADING(?, ?, ?)";
			CallableStatement cs = connect.prepareCall(sql);
			
			cs.setString(1, grading);
			cs.registerOutParameter(2, java.sql.Types.INTEGER);
			cs.setString(3, passingGrade);
			
			cs.execute();
			gradingId = cs.getInt(2);
		}
		catch(SQLException e){
			e.printStackTrace(); 	
		}
		return gradingId;	
	}

	public int getRoleId(int employeeId){
		int roleId = 0;
		
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
	
			String sql = "SELECT ROLE_ID FROM EMPLOYEE WHERE EMPLOYEE_ID = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setInt(1, employeeId);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				roleId = rs.getInt(1);
			}
	
		}
		catch(SQLException e){
			e.printStackTrace(); 	
		}
		
		return roleId;
	}

	public int getTypeOfEventId(String typeOfEvent){
		int typeOfEventId = 0;
		
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
	
			String sql = "SELECT TYPE_OF_EVENT_ID FROM TYPE_OF_EVENT WHERE TYPE_OF_EVENT = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setString(1, typeOfEvent);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				typeOfEventId = rs.getInt(1);
			}
	
		}
		catch(SQLException e){
			e.printStackTrace(); 	
		}
		
		return typeOfEventId;
	}
	
	public int getNumberOfMessages(int employeeId){
		int messages = 0;
		
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);
	
			String sql = "SELECT COUNT(*) FROM MESSAGE WHERE EMP_ID = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setInt(1, employeeId);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				messages = rs.getInt(1);
			}
	
		}
		catch(SQLException e){
			e.printStackTrace(); 	
		}
		
		return messages;
	}

	public ArrayList<Message> getMessages(int employeeId){
		ArrayList<Message> messages = new ArrayList<Message>();
		
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);

			String sql = "SELECT * FROM MESSAGE"
					+ " WHERE EMP_ID = ?";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setInt(1, employeeId);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Message m = new Message();
				String message = rs.getString(2);
				int messagerId = rs.getInt(4);
				
				m.setMessage(message);
				m.setMessagerId(messagerId);
				
				messages.add(m);
				
				m = null;
			}
			
			connect.commit();
		}
		catch(SQLException e){
			e.printStackTrace(); 	
		}
		
 		return messages;
	}

	public Message getMessager(Message msg){
		Message message = msg;
		
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);

			String sql = "SELECT * FROM EMPLOYEE"
					+ " WHERE EMPLOYEE_ID = ?";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setInt(1, msg.getMessagerId());
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String first = rs.getString(5);
				String last = rs.getString(6);
				String name = first + " " + last;
				
				message.setMessager(name);
			}
			
			connect.commit();
		}
		catch(SQLException e){
			e.printStackTrace(); 	
		}
		
		return message;
	}

	public ArrayList<Reimbursement> getReimbursements(int employeeId){
		ArrayList<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
			
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);

			String sql = "SELECT * FROM REIMBURSEMENT"
					+ " WHERE EMPLOYEE_ID = ?";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setInt(1, employeeId);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String event = rs.getString(4);
				String time = rs.getString(6);
				String description = rs.getString(9);
				int cost = rs.getInt(10);
				int locationId = rs.getInt(7);
				int gradingId = rs.getInt(11);
				int typeOfEventId = rs.getInt(12);
				int approvalStepId = rs.getInt(15);
				Date eventDate = rs.getDate(5);
				Date formDate = rs.getDate(8);
				int reimbId = rs.getInt(1);
				
				
				
			
				
				Reimbursement r = new Reimbursement(event, time, description, cost, locationId, 
												gradingId, typeOfEventId, approvalStepId, eventDate, formDate, reimbId);
				reimbursements.add(r);
				
				r = null;
			}
			
			connect.commit();
		}
		catch(SQLException e){
			e.printStackTrace(); 	
		}
		
		return reimbursements;
	}

	@Override
	public String getLocation(int locationId) {
		String location = "";
		
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);

			String sql = "SELECT LOCATION FROM LOCATION"
					+ " WHERE LOCATION_ID = ?";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setInt(1, locationId);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				location = rs.getString(1);
			}
			
			connect.commit();
		}
		catch(SQLException e){
			e.printStackTrace(); 	
		}
		
		return location;
	}

	@Override
	public String getGrading(int gradingId) {
		String grading = "";
		
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);

			String sql = "SELECT GRADING FROM GRADING"
					+ " WHERE GRADING_ID = ?";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setInt(1, gradingId);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				grading = rs.getString(1);
			}
			
			connect.commit();
		}
		catch(SQLException e){
			e.printStackTrace(); 	
		}
		
		return grading;
	}

	@Override
	public String getTypeOfEvent(int typeOfEventId) {
		String typeOfEvent = "";
		
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);

			String sql = "SELECT TYPE_OF_EVENT FROM TYPE_OF_EVENT"
					+ " WHERE TYPE_OF_EVENT_ID = ?";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setInt(1, typeOfEventId);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				typeOfEvent = rs.getString(1);
			}
			
			connect.commit();
		}
		catch(SQLException e){
			e.printStackTrace(); 	
		}
		
		return typeOfEvent;
	}

	@Override
	public String getApprovalStep(int approvalStepId) {
		String approvalStep = "";
		
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);

			String sql = "SELECT APPROVAL_STEP FROM APPROVAL_STEP"
					+ " WHERE APPROVAL_STEP_ID = ?";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setInt(1, approvalStepId);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				approvalStep = rs.getString(1);
			}
			
			connect.commit();
		}
		catch(SQLException e){
			e.printStackTrace(); 	
		}
		
		return approvalStep;
	}

	
	@Override
	public Reimbursement getReimbursementByid(int reimbId) {
		Reimbursement reimbursement = new Reimbursement();
		
		try(Connection connect = ConnectionUtil.getConnection();){
			connect.setAutoCommit(false);

			String sql = "SELECT * FROM REIMBURSEMENT"
					+ " WHERE REIMB_ID = ?";
			
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setInt(1, reimbId);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String event = rs.getString(4);
				String time = rs.getString(6);
				String description = rs.getString(9);
				int cost = rs.getInt(10);
				int locationId = rs.getInt(7);
				int gradingId = rs.getInt(11);
				int typeOfEventId = rs.getInt(12);
				int approvalStepId = rs.getInt(15);
				Date eventDate = rs.getDate(5);
				Date formDate = rs.getDate(8);
				
				reimbursement.setReimbId(reimbId);
				reimbursement.setEvent(event);
				reimbursement.setTime(time);
				reimbursement.setDescription(description);
				reimbursement.setCost(cost);
				reimbursement.setLocationId(locationId);
				reimbursement.setGradingId(gradingId);
				reimbursement.setTypeOfEventId(typeOfEventId);
				reimbursement.setApprovalStepId(approvalStepId);
				reimbursement.setEventDate(eventDate);
				reimbursement.setFormDate(formDate);
			}
			
			connect.commit();
		}
		catch(SQLException e){
			e.printStackTrace(); 	
		}
		
		return reimbursement;
	}

	
}