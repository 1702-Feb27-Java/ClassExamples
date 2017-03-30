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
	
	// calculate the projected reimbursement
	double calculateRe(AppClass ac);
	
	AppClass getAppByAppID(int appID);
	
	ArrayList<AppClass> getAppsByUserID(UserClass uc);
	ArrayList<Integer> getAppIDsByUserID(UserClass uc);
	
	// updating approval status, level, and resolver
	void approveAsManager(int appID, int apprLvl, UserClass uc, String message);
	
	// if the first method is successful, call the second.
	void updateAsManager(int appID, int apprLvl);
	
	// BENCO ONLY
	void approveAsBenco();
	
	// returns all the approvals updates for ONE app
	ArrayList<ApprovalClass> getApprovalsByAppID(int appID);
	
	// returns the grading info for one application
	GradingClass getGradingByAppID(int appID);
	ReimbursementClass getReimbByAppID(int appID);
	
	// UserClass uc is the manager who is currently logged in
	ArrayList<AppClass> getPendingAppsByManager(int apprLvl, int apprSts, UserClass uc);
	
	//write getPendingAppsByBenco?
	ArrayList<AppClass> getPendingAppsByBenco(UserClass uc);
}