package com.revature.dao;

import java.util.ArrayList;

import com.revature.pojo.AttachmentClass;
import com.revature.pojo.GradeAttachClass;

public interface AttachDAO {
	
	void addAttachments(int appID, String url);
	void addGrAttachments(int appID, String url);
	
	ArrayList<AttachmentClass> getAttachments(int appID);
	
	ArrayList<GradeAttachClass> getGradeAttachments(int appID);
	
	String generateFileURL(String keyName);

}
