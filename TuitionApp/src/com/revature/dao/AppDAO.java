package com.revature.dao;

import java.util.ArrayList;

import com.revature.pojo.AppClass;
import com.revature.pojo.ApprovalClass;
import com.revature.pojo.CDTClass;
import com.revature.pojo.GradingClass;
import com.revature.pojo.ReimbursementClass;
import com.revature.pojo.UserClass;

public interface AppDAO {
	
	// add
	void addApp(UserClass uc, AppClass ac, CDTClass cdt, GradingClass grading);
	
	void cancelApp(int appID);
	
	// calculate the projected reimbursement
	double calculateRe(AppClass ac);
	
	AppClass getAppByAppID(int appID);
	
	ArrayList<AppClass> getAppsByUserID(UserClass uc);
	ArrayList<Integer> getAppIDsByUserID(UserClass uc);
	
	/**************************************************
	 *  APPROVAL AND DENY LOGIC
	 **************************************************/
	
	// updating approval status, level, and resolver
	void approveAsManager(int appID, int apprLvl, UserClass uc, String message);
	
	// if the first method is successful, call the second.
	void updateAsManager(int appID, int apprLvl);
	
	// BENCO ONLY
	void approveAsBenco(int appID, int apprLvl, UserClass uc, String message);
	
	void awardAsBenco(int appID);
	
	void denyAsManager(int appID, int apprLvl, UserClass uc, String message);
		
	/**************************************************
	 *  END APPROVAL AND DENY LOGIC
	 **************************************************/
	
	void changeAward(int appID, double amount, String reason);
	
	// returns all the approvals updates for ONE app
	ArrayList<ApprovalClass> getApprovalsByAppID(int appID);
	
	// returns the grading info for one application
	GradingClass getGradingByAppID(int appID);
	ReimbursementClass getReimbByAppID(int appID);
	
	// UserClass uc is the manager who is currently logged in
	ArrayList<AppClass> getPendingApps(int apprLvl, int apprSts, UserClass uc);
	
}
