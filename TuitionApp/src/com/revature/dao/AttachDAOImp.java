package com.revature.dao;

import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.revature.connection.ConnectionClass;
import com.revature.pojo.AttachmentClass;
import com.revature.pojo.GradeAttachClass;

public class AttachDAOImp implements AttachDAO {
	
	CallableStatement addAttach, addGrAttach;
	PreparedStatement getAttach, getGrAttach;

	@Override
	public void addAttachments(int appID, String url) {
		// TODO Auto-generated method stub
		
		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);
			
			addAttach = connect.prepareCall("CALL addAttachments(?,?)");
			
			addAttach.setInt(1, appID);
			addAttach.setString(2, url);
			
			addAttach.execute();
		
		connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
	}
	
	@Override
	public void addGrAttachments(int appID, String url) {
		// TODO Auto-generated method stub
		
		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);
			
			addGrAttach = connect.prepareCall("CALL addGradeAttachments(?,?)");
			
			addGrAttach.setInt(1, appID);
			addGrAttach.setString(2, url);
			
			addGrAttach.execute();
		
		connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
	}

	@Override
	public ArrayList<AttachmentClass> getAttachments(int appID) {
		// TODO Auto-generated method stub
		
		ArrayList<AttachmentClass> attachments = new ArrayList<AttachmentClass>();
		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);
			
		String sql = "SELECT attachment_id, app_id, attachment_url FROM Attachments WHERE app_id IN (SELECT app_id FROM Attachments where app_id = ?)";
		getAttach = connect.prepareStatement(sql);
		getAttach.setInt(1, appID);
		
		ResultSet rs = getAttach.executeQuery();
		
		while(rs.next()){
			AttachmentClass ac = new AttachmentClass();
			
			ac.setAttachID(rs.getInt(1));
			ac.setAppID(rs.getInt(2));
			ac.setAttachURL(rs.getString(3));
			attachments.add(ac);
			
			ac = null;
		}
		
		connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
		return attachments;
	}

	@Override
	public ArrayList<GradeAttachClass> getGradeAttachments(int appID) {
		// TODO Auto-generated method stub
		
		ArrayList<GradeAttachClass> grAttachments = new ArrayList<GradeAttachClass>();
		try (Connection connect = ConnectionClass.getConnection();) {
			connect.setAutoCommit(false);
			
		String sql = "SELECT grade_attach_id, app_id, grade_attach_url FROM GradeAttachments WHERE app_id IN (SELECT app_id FROM GradeAttachments where app_id = ?)";
		getGrAttach = connect.prepareStatement(sql);
		getGrAttach.setInt(1, appID);
		
		ResultSet rs = getGrAttach.executeQuery();
		
		while(rs.next()){
			GradeAttachClass gr = new GradeAttachClass();
			
			gr.setGradeAttachID(rs.getInt(1));
			gr.setAppID(rs.getInt(2));
			gr.setGradeAttachURL(rs.getString(3));
			grAttachments.add(gr);
			
			gr = null;
		}
		
		connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
		return grAttachments;
	}

	@Override
	public String generateFileURL(String keyName) {
		
		final String bucketName = "trmsproject";
		String downloadURL = null;
		
		AmazonS3 s3client = new AmazonS3Client(new ProfileCredentialsProvider());

		try {
			System.out.println("Generating pre-signed URL.");
			java.util.Date expiration = new java.util.Date();
			long milliSeconds = expiration.getTime();
			milliSeconds += 1000 * 60 * 60; // Add 1 hour.
			expiration.setTime(milliSeconds);

			GeneratePresignedUrlRequest generatePresignedUrlRequest = 
				    new GeneratePresignedUrlRequest(bucketName, keyName);
			generatePresignedUrlRequest.setMethod(HttpMethod.GET); 
			generatePresignedUrlRequest.setExpiration(expiration);

			URL url = s3client.generatePresignedUrl(generatePresignedUrlRequest); 

			System.out.println("Pre-Signed URL = " + url.toString());
			
			downloadURL = url.toString();
		} catch (AmazonServiceException exception) {
			System.out.println("Caught an AmazonServiceException, " +
					"which means your request made it " +
					"to Amazon S3, but was rejected with an error response " +
			"for some reason.");
			System.out.println("Error Message: " + exception.getMessage());
			System.out.println("HTTP  Code: "    + exception.getStatusCode());
			System.out.println("AWS Error Code:" + exception.getErrorCode());
			System.out.println("Error Type:    " + exception.getErrorType());
			System.out.println("Request ID:    " + exception.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, " +
					"which means the client encountered " +
					"an internal error while trying to communicate" +
					" with S3, " +
			"such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}
		// TODO Auto-generated method stub
		return downloadURL;
	}

}
