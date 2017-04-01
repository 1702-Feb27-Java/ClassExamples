package DAO;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import User.Messages;
import User.UserInfo;
import User.appinfo;

public interface DAOcalls {
	
	//signEmployeeIn and capture info
	UserInfo getUserInfo(UserInfo current);
	//create request
	UserInfo CreateRequest();
	//update user
	UserInfo ApplicationUpdate(appinfo newapp, HttpServletRequest request);
//	void updatestuff();
	
//////////get the approval information for supe, head, and ben co//////////////////////////
	ArrayList<appinfo> getAllInfoBenCo();
	
	appinfo setApprovalLevelBenCo();
	
	ArrayList<appinfo> getAllInfoSupervisor();

	appinfo setApprovalLevelSupervisor();

	
	ArrayList<appinfo> getAllInfoHead();

	appinfo setApprovalLevelHead();

	
	//delete information
	//select
	
	
	//ArrayList<Messages> viewMessages();
	ArrayList<Messages> viewMessages(int sender);

	
}
