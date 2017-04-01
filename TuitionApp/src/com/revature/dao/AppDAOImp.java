package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.connection.ConnectionClass;
import com.revature.pojo.AppClass;
import com.revature.pojo.ApprovalClass;
import com.revature.pojo.CDTClass;
import com.revature.pojo.GradingClass;
import com.revature.pojo.ReimbursementClass;
import com.revature.pojo.UserClass;

public class AppDAOImp implements AppDAO {

	PreparedStatement findAppID, getAwarded, getApprovals, 
	getAppIDs, getApps, getGrading, getApp, getPending, getPendingBenco, cancel;
	CallableStatement newApp, newCDT, newGrading, newApprovals, newRe, 
	approveAsManager, setNextAppr, approveAsBenco, awardAsBenco, denyAsManager, denyAsBenco;

	@Override
	public void addApp(UserClass uc, AppClass ac, CDTClass cdt, GradingClass grading) {
		// TODO Auto-generated method stub
		try (Connection connect = ConnectionClass.getConnection();) {
			// TODO Auto-generated method stub
			connect.setAutoCommit(false);

			newApp = connect.prepareCall("CALL addApp(?, ?, ?, ?, ?, ?, ?, ?)");

			newApp.setInt(1, uc.getUserID());
			newApp.setInt(2, ac.getPriority());
			newApp.setString(3, ac.getDateCreated());
			newApp.setInt(4, ac.getStatusID());
			newApp.setInt(5, ac.getEventID());
			newApp.setString(6, ac.getLoc());
			newApp.setDouble(7, ac.getTotalCost());
			newApp.setString(8, ac.getJustification());

			newApp.execute();

			int storedAppID = 0; // gotta find this
			findAppID = connect.prepareStatement("SELECT MAX(app_id) FROM Applications");
			ResultSet rs = findAppID.executeQuery();

			while (rs.next()) {
				storedAppID = rs.getInt(1);
			}

			newCDT = connect.prepareCall("CALL addClassDateTime(?,?,?,?)");

			newCDT.setInt(1, storedAppID);
			newCDT.setString(2, cdt.getStartdate());
			newCDT.setString(3, cdt.getEnddate());
			newCDT.setInt(4, cdt.getHoursPerWeek());

			newCDT.execute();

			newGrading = connect.prepareCall("CALL addGrading(?,?,?)");

			newGrading.setInt(1, storedAppID);
			newGrading.setInt(2, grading.getGradingFormatID());
			newGrading.setString(3, grading.getGradeCutoff());

			newGrading.execute();

			// set default approval status
			newApprovals = connect.prepareCall("CALL addApprovals(?,?,?)");

			newApprovals.setInt(1, storedAppID);
			newApprovals.setInt(2, 1);
			newApprovals.setInt(3, 1);

			newApprovals.execute();

			double projected = calculateRe(ac);

			newRe = connect.prepareCall("CALL addReimbursement(?,?)");
			newRe.setInt(1, storedAppID);
			newRe.setDouble(2, projected);
			
			newRe.execute();

			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
	}

	@Override
	public ArrayList<AppClass> getAppsByUserID(UserClass uc) {
		// TODO Auto-generated method stub
		ArrayList<AppClass> appsForUser = new ArrayList<AppClass>();

		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);

			getApps = connect.prepareStatement(
					"SELECT app_id, priority_id, date_created, status_id, event_id, loc, total_cost, grading_id, justification, reimbursement_id FROM Applications WHERE user_id IN (SELECT user_id FROM Applications WHERE user_id = ?)");

			getApps.setInt(1, uc.getUserID());

			ResultSet rs = getApps.executeQuery();

			while (rs.next()) {
				AppClass app = new AppClass();

				app.setAppID(rs.getInt(1));
				app.setPriority(rs.getInt(2));
				app.setDateCreated(rs.getString(3));
				app.setStatusID(rs.getInt(4));
				app.setEventID(rs.getInt(5));
				app.setLoc(rs.getString(6));
				app.setTotalCost(rs.getDouble(7));
				app.setGradingID(rs.getInt(8));
				app.setJustification(rs.getString(9));
				app.setReimburseID(rs.getInt(10));

				appsForUser.add(app);

				app = null;
			}

			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
		return appsForUser;
	}

	@Override
	public double calculateRe(AppClass ac) {
		// TODO Auto-generated method stub
		// ArrayList<Double> allAwarded = new ArrayList<Double>();

		double awarded = 0;
		double projected = 0;
//		try (Connection connect = ConnectionClass.getConnection();) {
//			connect.setAutoCommit(false);

//			getAwarded = connect.prepareStatement("CALL getAwarded(?)");
//
//			getAwarded.setInt(1, ac.getAppID());
//
//			ResultSet rs = getAwarded.executeQuery();
//
//			while (rs.next()) {
//				awarded = rs.getDouble(1);
//			}

			double Re[] = calculateProjected(ac.getTotalCost(), awarded, ac.getEventID());
			projected = Re[0];

//			connect.commit();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (NullPointerException e2) {
//			e2.printStackTrace();
//		}
		return projected;
	}

	// calculates reimbursement
	public static double[] calculateProjected(double cost, double awarded, int event) {

		// subtract already awarded reimbursement for this year
		double leftOver = 1000 - awarded;

		// initialize pending and projected
		double pending = 0;
		double projected = 0;
		double available = 0;
		if (event == 1) { // university courses
			pending = .8 * cost;
		} else if (event == 2) { // seminars
			pending = .6 * cost;
		} else if (event == 3) { // certification prep
			pending = .75 * cost;
		} else if (event == 4) { // certification
			pending = 1 * cost;
		} else if (event == 5) { // technical training
			pending = .9 * cost;
		} else { // other
			pending = .3 * cost;
		}

		if (pending <= leftOver) {
			available = leftOver - pending;
			projected = pending;
		} else {
			available = 0;
			projected = leftOver;
		}

		double math[] = { projected, available };

		return math;
	}

	@Override
	public ArrayList<ApprovalClass> getApprovalsByAppID(int appID) {
		// TODO Auto-generated method stub

		ArrayList<ApprovalClass> approvals = new ArrayList<ApprovalClass>();

		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);

			getApprovals = connect.prepareStatement(
					"SELECT APPROVAL_APP_ID, APPROVAL_LEVEL, APPROVAL_STATUS, APPROVER_ID, APPROVAL_MESSAGE FROM Approvals WHERE app_id IN (SELECT app_id FROM Approvals WHERE app_id = ?)");

			getApprovals.setInt(1, appID);

			ResultSet rs = getApprovals.executeQuery();

			while (rs.next()) {

				ApprovalClass approval = new ApprovalClass();
				approval.setApprovalID(rs.getInt(1));
				approval.setApprovalLevel(rs.getInt(2));
				approval.setApprovalStatus(rs.getInt(3));
				approval.setApproverID(rs.getInt(4));
				approval.setApprovalMessage(rs.getString(5));

				approvals.add(approval);

				approval = null;
			}

			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}

		return approvals;
	}

	@Override
	public ArrayList<Integer> getAppIDsByUserID(UserClass uc) {
		// TODO Auto-generated method stub

		ArrayList<Integer> appIDs = new ArrayList<Integer>();
		// int[] appIDs = null;

		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);

			getAppIDs = connect.prepareStatement(
					"SELECT app_id FROM Applications WHERE user_id IN (SELECT user_id FROM Applications WHERE user_id = ?)");

			getAppIDs.setInt(1, uc.getUserID());
			ResultSet rs = getAppIDs.executeQuery();

			while (rs.next()) {
				appIDs.add(rs.getInt(1));
			}

			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}

		return appIDs;
	}

	@Override
	public GradingClass getGradingByAppID(int appID) {
		// TODO Auto-generated method stub

		GradingClass gc = new GradingClass();

		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);

			String sql = "SELECT app_id, grading_format_id, grade_cutoff, grade_awarded, presentation_review FROM (SELECT apps.app_id, gr.grading_format_id, gr.grade_cutoff, gr.grade_awarded, gr.presentation_review FROM Applications apps INNER JOIN grading gr ON apps.grading_id = gr.grading_id) WHERE app_id IN (SELECT app_id FROM Applications WHERE app_id = ?)";
			getGrading = connect.prepareStatement(sql);
			
			getGrading.setInt(1, appID);
			
			ResultSet rs = getGrading.executeQuery();
			
			while (rs.next()){
				gc.setAppID(rs.getInt(1));
				gc.setGradingFormatID(rs.getInt(2));
				gc.setGradeCutoff(rs.getString(3));
				gc.setGradeAwarded(rs.getString(4));
				gc.setPresReview(rs.getString(5));
			}

			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}

		return gc;
	}

	@Override
	public ReimbursementClass getReimbByAppID(int appID) {
		// TODO Auto-generated method stub
		ReimbursementClass rc = new ReimbursementClass();

		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);

			String sql = "SELECT app_id, reimbursement_id, projected_reimbursement, awarded_reimbursement, change_reason FROM (SELECT apps.app_id, re.reimbursement_id, re.projected_reimbursement, re.awarded_reimbursement, re.change_reason FROM Applications apps INNER JOIN Reimbursements re ON apps.reimbursement_id = re.reimbursement_id) WHERE app_id IN (SELECT app_id FROM Applications WHERE app_id = ?)";

			getGrading = connect.prepareStatement(sql);
			
			getGrading.setInt(1, appID);
			
			ResultSet rs = getGrading.executeQuery();
			
			while(rs.next()){
				rc.setReimburseID(rs.getInt(2));
				rc.setProjected(rs.getDouble(3));
				rc.setAwarded(rs.getDouble(4));
				rc.setChangeReason(rs.getString(5));
			}

			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}

		return rc;
	}


	@Override
	public AppClass getAppByAppID(int appID) {
		// TODO Auto-generated method stub
		
		AppClass ac = new AppClass();
		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);
			
			getApp = connect.prepareStatement("SELECT * FROM Applications WHERE app_id = ? ");
			
			getApp.setInt(1, appID);
			
			ResultSet rs = getApp.executeQuery();
			
			while (rs.next()){
				ac.setAppID(rs.getInt(1));
				ac.setUserID(rs.getInt(2));
				ac.setDateCreated(rs.getString(3));
				ac.setStatusID(rs.getInt(4));
				ac.setPriority(rs.getInt(5));
				ac.setEventID(rs.getInt(6));
				ac.setCdtID(rs.getInt(7));
				ac.setLoc(rs.getString(8));
				ac.setTotalCost(rs.getDouble(9));
				ac.setGradingID(rs.getInt(10));
				ac.setJustification(rs.getString(11));
				ac.setReimburseID(rs.getInt(12));
			}
		
			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
		return ac;
	}


	@Override
	public ArrayList<AppClass> getPendingAppsByManager(int apprLvl, int apprSts, UserClass uc) {
		// TODO Auto-generated method stub
		
		ArrayList<AppClass> pendingApps = new ArrayList<AppClass>();
		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);
			
			String sql = "SELECT * FROM (SELECT us.user_id, us.dept_id, apps.app_id, apps.priority_id, apps.date_created, apps.status_id, apps.event_id, apps.total_cost, apps.justification, appr.approval_level, appr.approval_status FROM Applications apps INNER JOIN Users us ON us.user_id = apps.user_id INNER JOIN Approvals appr ON apps.app_id = appr.app_id) WHERE status_id = 1 AND approval_level = ? AND approval_status = ? AND dept_id = ?";
		
			getPending = connect.prepareStatement(sql);
			getPending.setInt(1, apprLvl);
			getPending.setInt(2, apprSts);
			getPending.setInt(3, uc.getDeptID());
			
			ResultSet rs = getPending.executeQuery();
			
			while (rs.next()){
				AppClass ac = new AppClass();
				
				ac.setUserID(rs.getInt(1));
				ac.setAppID(rs.getInt(3));
				ac.setPriority(rs.getInt(4));
				ac.setDateCreated(rs.getString(5));
				ac.setStatusID(rs.getInt(6));
				ac.setEventID(rs.getInt(7));
				ac.setTotalCost(rs.getDouble(8));
				ac.setJustification(rs.getString(9));
				
				pendingApps.add(ac);
				
				ac = null;
			}
					
			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
		return pendingApps;
	}

	@Override
	public void approveAsManager(int appID, int apprLvl, UserClass uc, String message) {
		// TODO Auto-generated method stub

		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);
			
			approveAsManager = connect.prepareCall("CALL approveAsManager(?,?,?,?)");
			approveAsManager.setInt(1, appID);
			approveAsManager.setInt(2, apprLvl); 
			approveAsManager.setInt(3, uc.getUserID());
			approveAsManager.setString(4, message);
			
			approveAsManager.execute();
			
			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
	}

	public void updateAsManager(int appID, int apprLvl) {
		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);
			
			setNextAppr = connect.prepareCall("CALL setAppToNextAppr(?, ?)");
			setNextAppr.setInt(1, appID);
			setNextAppr.setInt(2, apprLvl);
			setNextAppr.execute();

			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
	}

	@Override
	public ArrayList<AppClass> getPendingAppsByBenco(UserClass uc) {
		// TODO Auto-generated method stub
		
		ArrayList<AppClass> pendingApps = new ArrayList<AppClass>();
		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);

			String sql = "SELECT * FROM (SELECT us.user_id, us.dept_id, apps.app_id, apps.priority_id, apps.date_created, apps.status_id, apps.event_id, apps.total_cost, apps.justification, appr.approval_level, appr.approval_status FROM Applications apps INNER JOIN Users us ON us.user_id = apps.user_id INNER JOIN Approvals appr ON apps.app_id = appr.app_id) WHERE status_id = 1 AND approval_level = 3 AND approval_status = 1";
			getPendingBenco = connect.prepareStatement(sql);

			ResultSet rs = getPendingBenco.executeQuery();

			while (rs.next()) {
				AppClass ac = new AppClass();
				// if the application is the Benco user's own app
				if (uc.getUserID() == rs.getInt(1)){
					// then we can't add it to his/her pending tasks
					continue;
				}
				else {
					ac.setUserID(rs.getInt(1));
					ac.setAppID(rs.getInt(3));
					ac.setPriority(rs.getInt(4));
					ac.setDateCreated(rs.getString(5));
					ac.setStatusID(rs.getInt(6));
					ac.setEventID(rs.getInt(7));
					ac.setTotalCost(rs.getDouble(8));
					ac.setJustification(rs.getString(9));

					pendingApps.add(ac);
				}
				
				ac = null;
			}
			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
		return pendingApps;
	}
	
	@Override
	public void approveAsBenco(int appID, int apprLvl, UserClass uc, String message) {
		// TODO Auto-generated method stub
		// must approve as benco
		// AND add awarded reimbursement
		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);
		
			approveAsBenco = connect.prepareCall("CALL approveAsBenco(?,?,?,?)");
			approveAsBenco.setInt(1, appID);
			approveAsBenco.setInt(2, apprLvl); 
			approveAsBenco.setInt(3, uc.getUserID());
			approveAsBenco.setString(4, message);
			
			approveAsBenco.execute();
		
			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
	}
	
	public void awardAsBenco(int appID){
		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);
		
			awardAsBenco = connect.prepareCall("CALL awardAsBenco(?)");
			awardAsBenco.setInt(1, appID);
			
			awardAsBenco.execute();
		
			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
	}

	@Override
	public void denyAsManager(int appID, int apprLvl, UserClass uc, String message) {
		// TODO Auto-generated method stub
		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);
			
			System.out.println(appID + " " + apprLvl + " " + uc.getUserID() + " " + message) ;
			denyAsManager = connect.prepareCall("CALL denyAsManager(?, ?, ?, ?)");
			denyAsManager.setInt(1, appID);
			denyAsManager.setInt(2, apprLvl);
			denyAsManager.setInt(3, uc.getUserID());
			denyAsManager.setString(4, message);
			denyAsManager.execute();
			
			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
	}

	@Override
	public void cancelApp(int appID) {
		// TODO Auto-generated method stub
		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);
			
			cancel = connect.prepareStatement("UPDATE Applications SET status_id = 2 WHERE app_id = ?");
			cancel.setInt(1, appID);
			
			cancel.execute();
			
			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
	}

}
