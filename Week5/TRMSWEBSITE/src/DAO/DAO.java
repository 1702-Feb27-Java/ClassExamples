package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Employee.*;

public class DAO {
	private Connection cont;
	public  void attachfile(int val, String filename)
	{
		
		
	
    
		try{
	    	 cont = ConnectionUtil.getConnection();
	    	PreparedStatement ps = cont.prepareStatement("INSERT INTO ATTACHMENTS(FILE_LOCATION,REIN_ID )VALUES (?,?)");
	    
	    	ps.setString(1, filename);
	    	ps.setInt(2, Collecters.rein.get(val).getREIN_ID());
	       
            ResultSet rs = ps.executeQuery();
	  
	    	}
	    	catch(Exception e)
	    	{
	    		
	    		System.out.println(e.getStackTrace());
	    	System.out.println(e.getMessage() + " aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" );
	    		
	    	}
		

		 


		  Employee.Collecters.emp = new ArrayList<Employee.Employees>();
    	  Employee.Collecters.rein =new ArrayList<Employee.Reinbursment>();
    	  pullAllReinDown();
    	  pullAllEmpDown();
		try {
			cont.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	public void rejectVal(int val, String message)
	{
		 int temp =		Employee.Collecters.rein.get(val).getAPP_ID();
		 int reinid = Employee.Collecters.rein.get(val).getREIN_ID();
		 temp++;
		 Employee.Collecters.rein.get(val).setAPP_ID(temp);
		String sql = "UPDATE REINBURSMENT SET APP_ID = "+7+ "WHERE REIN_ID = "+ reinid ;

		try {

			Connection cont = ConnectionUtil.getConnection();

			PreparedStatement ps = cont.prepareStatement(sql);
			// ps.setString(1, user);
			// ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			
		}
	
    	  Employee.Collecters.emp = new ArrayList<Employee.Employees>();
    	  Employee.Collecters.rein =new ArrayList<Employee.Reinbursment>();
    	  pullAllReinDown();
    	  pullAllEmpDown();
		try{
	    	Connection cont = ConnectionUtil.getConnection();
	    	PreparedStatement ps = cont.prepareStatement("INSERT INTO MESSAGES(MESSAGE,EMP_ID,REIN_ID,APP_ID )VALUES (?,?,?,?)");
	    	ps.setString(1, message);
	    	ps.setInt(2, Collecters.rein.get(val).getEMP_ID());       
	        ps.setInt(3, Collecters.rein.get(val).getREIN_ID());
	        ps.setInt(4, Collecters.rein.get(val).getAPP_ID());
            ResultSet rs = ps.executeQuery();
	  
	    	}
	    	catch(Exception e)
	    	{
	    		
	    		System.out.println(e.getStackTrace());
	    	System.out.println(e.getMessage() + " aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" );
	    		
	    	}
		

		 


		  Employee.Collecters.emp = new ArrayList<Employee.Employees>();
    	  Employee.Collecters.rein =new ArrayList<Employee.Reinbursment>();
    	  pullAllReinDown();
    	  pullAllEmpDown();
	    	  
	    }
	
	
	
	public void empReject(int val)
	{
		 int temp =		Employee.Collecters.rein.get(val).getAPP_ID();
		 int reinid = Employee.Collecters.rein.get(val).getREIN_ID();
		 temp++;
		 Employee.Collecters.rein.get(val).setAPP_ID(temp);
		String sql = "UPDATE REINBURSMENT SET APP_ID = "+7+ "WHERE REIN_ID = "+ reinid ;

		try {

			 cont = ConnectionUtil.getConnection();

			PreparedStatement ps = cont.prepareStatement(sql);
			// ps.setString(1, user);
			// ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			
		}
	
    	  Employee.Collecters.emp = new ArrayList<Employee.Employees>();
    	  Employee.Collecters.rein =new ArrayList<Employee.Reinbursment>();
    	  pullAllReinDown();
    	  pullAllEmpDown();
  		try {
			cont.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			    	  
	    }
///////////////////////////////////////////////
	public void failone (int val,int cost)
	{
		
 int temp =		Employee.Collecters.rein.get(val).getAPP_ID();
 int reinid = Employee.Collecters.rein.get(val).getREIN_ID();
 temp++;
 Employee.Collecters.rein.get(val).setAPP_ID(temp);
 

	String sql = "";	
	
	 Employee.Collecters.emp = new ArrayList<Employee.Employees>();
	  Employee.Collecters.rein =new ArrayList<Employee.Reinbursment>();
	  pullAllReinDown();
	  pullAllEmpDown();
	//////////////////////////////////////

	 sql = "UPDATE EMPLOYEE SET APPROVEDAMMOUNT = "+cost+ "WHERE EMP_ID = "+ Collecters.rein.get(val).getEMP_ID();

	try {

		cont = ConnectionUtil.getConnection();

		PreparedStatement ps = cont.prepareStatement(sql);
		// ps.setString(1, user);
		// ps.setString(2, password);

		ResultSet rs = ps.executeQuery();
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage());
		
	}
    		
	///////////////////////////
	
	
	 Employee.Collecters.emp = new ArrayList<Employee.Employees>();
	  Employee.Collecters.rein =new ArrayList<Employee.Reinbursment>();
	  pullAllReinDown();
	  pullAllEmpDown();
		try {
			cont.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

 
	}

		
	
///////////////////////////////////////
	public void bencoapproveVal (int val,int cost)
	{
		
 int temp =		Employee.Collecters.rein.get(val).getAPP_ID();
 int reinid = Employee.Collecters.rein.get(val).getREIN_ID();
 temp++;
 Employee.Collecters.rein.get(val).setAPP_ID(temp);
 

	String sql = "UPDATE REINBURSMENT SET APP_ID = "+ 8 + "WHERE REIN_ID = "+ reinid ;

	try {

		 cont = ConnectionUtil.getConnection();

		PreparedStatement ps = cont.prepareStatement(sql);
		// ps.setString(1, user);
		// ps.setString(2, password);

		ResultSet rs = ps.executeQuery();
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage());
		
	}
	
	
	 Employee.Collecters.emp = new ArrayList<Employee.Employees>();
	  Employee.Collecters.rein =new ArrayList<Employee.Reinbursment>();
	  pullAllReinDown();
	  pullAllEmpDown();
	//////////////////////////////////////

	 sql = "UPDATE EMPLOYEE SET APPROVEDAMMOUNT = "+cost+ "WHERE EMP_ID = "+ Collecters.rein.get(val).getEMP_ID();

	try {

		 cont = ConnectionUtil.getConnection();

		PreparedStatement ps = cont.prepareStatement(sql);
		// ps.setString(1, user);
		// ps.setString(2, password);

		ResultSet rs = ps.executeQuery();
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage());
		
	}
    		
	///////////////////////////
	
	
	 Employee.Collecters.emp = new ArrayList<Employee.Employees>();
	  Employee.Collecters.rein =new ArrayList<Employee.Reinbursment>();
	  pullAllReinDown();
	  pullAllEmpDown();
		try {
			cont.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

 
	}

	public void employeeAccept(int val , double cost)
	{
	
		
		int emp_id = Employee.SignedInEmployee.empID;
		String sql = "UPDATE EMPLOYEE SET REIN_ALLOWANCE = "+ SignedInEmployee.reinallowance + "WHERE EMP_ID = "+  emp_id;

		try {

			 cont = ConnectionUtil.getConnection();

			PreparedStatement ps = cont.prepareStatement(sql);
			// ps.setString(1, user);
			// ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			
		}
		
		try {
			cont.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	
	
	
	
	
	
	///////////////////////////////////
	//
	
	
	public void approveEmpBencoVal (int val)
	{
		
 int temp =		Employee.Collecters.rein.get(val).getAPP_ID();
 int reinid = Employee.Collecters.rein.get(val).getREIN_ID();
 temp++;
 Employee.Collecters.rein.get(val).setAPP_ID(temp);
 
 


	String sql = "UPDATE EMPLOYEE SET REIN_ALLOWANCE =  "+ (SignedInEmployee.reinallowance - SignedInEmployee.am) + "WHERE EMP_ID = "+ Employee.Collecters.rein.get(val).getEMP_ID() ;

	try {

		 cont = ConnectionUtil.getConnection();

		PreparedStatement ps = cont.prepareStatement(sql);
		// ps.setString(1, user);
		// ps.setString(2, password);

		ResultSet rs = ps.executeQuery();
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage());
		
	}
	approveSuccess(val);
	 Employee.Collecters.emp = new ArrayList<Employee.Employees>();
	  Employee.Collecters.rein =new ArrayList<Employee.Reinbursment>();
	  pullAllReinDown();
	  pullAllEmpDown();
		try {
			cont.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

 
	}
	
	
	
	/////////////////////////////////////////
	
	public void approveSuccess (int val)
	{
		
 int temp =		Employee.Collecters.rein.get(val).getAPP_ID();
 int reinid = Employee.Collecters.rein.get(val).getREIN_ID();
 temp++;
 Employee.Collecters.rein.get(val).setAPP_ID(temp);
 

	String sql = "UPDATE REINBURSMENT SET APP_ID = "+5+ "WHERE REIN_ID = "+ reinid ;

	try {

		 cont = ConnectionUtil.getConnection();

		PreparedStatement ps = cont.prepareStatement(sql);
		// ps.setString(1, user);
		// ps.setString(2, password);

		ResultSet rs = ps.executeQuery();
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage());
		
	}
	 
	 Employee.Collecters.emp = new ArrayList<Employee.Employees>();
	  Employee.Collecters.rein =new ArrayList<Employee.Reinbursment>();
	  pullAllReinDown();
	  pullAllEmpDown();
		try {
			cont.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	///////////////////////////////////////
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void approveVal (int val, int cost)
	{
		
 int temp =		Employee.Collecters.rein.get(val).getAPP_ID();
 int reinid = Employee.Collecters.rein.get(val).getREIN_ID();
 temp++;
 Employee.Collecters.rein.get(val).setAPP_ID(temp);
 

	String sql = "UPDATE REINBURSMENT SET APP_ID = "+temp+ "WHERE REIN_ID = "+ reinid ;

	try {

		 cont = ConnectionUtil.getConnection();

		PreparedStatement ps = cont.prepareStatement(sql);
		// ps.setString(1, user);
		// ps.setString(2, password);

		ResultSet rs = ps.executeQuery();
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage());
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	if(temp == 5)
	{
	 int appam = 0;
	 int reinall = 0;
		for(int i=0; i<Collecters.emp.size();i++)
		{
			
			
			if(Collecters.rein.get(val).getEMP_ID() == Collecters.emp.get(i).getEMP_ID())
			{
				
				appam =Collecters.emp.get(i).getApprovedammount();
				reinall = Collecters.emp.get(i).getREIN_ALLOWANCE();
			}
		}
		
		sql = "UPDATE EMPLOYEE SET REIN_ALLOWANCE =  "+ (reinall - appam) + "WHERE EMP_ID = "+ Employee.Collecters.rein.get(val).getEMP_ID() ;

		try {

			 cont = ConnectionUtil.getConnection();

			PreparedStatement ps = cont.prepareStatement(sql);
			// ps.setString(1, user);
			// ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			
		}
	}
		approveSuccess(val);
		 Employee.Collecters.emp = new ArrayList<Employee.Employees>();
		  Employee.Collecters.rein =new ArrayList<Employee.Reinbursment>();
		  pullAllReinDown();
		  pullAllEmpDown();
			try {
				cont.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
	}

	
	
	
	
	
	
	
	
	
	///////////////////////////////////////////////////
	public void approveVal (int val)
	{
		
 int temp =		Employee.Collecters.rein.get(val).getAPP_ID();
 int reinid = Employee.Collecters.rein.get(val).getREIN_ID();
 temp++;
 Employee.Collecters.rein.get(val).setAPP_ID(temp);
 

	String sql = "UPDATE REINBURSMENT SET APP_ID = "+temp+ "WHERE REIN_ID = "+ reinid ;

	try {

		 cont = ConnectionUtil.getConnection();

		PreparedStatement ps = cont.prepareStatement(sql);
		// ps.setString(1, user);
		// ps.setString(2, password);

		ResultSet rs = ps.executeQuery();
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage());
		
	}
	try {
		cont.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public void pullAllEmpDown() {

		String s = "";
		String pass = "";
		int roleid = -1;
		int deptid = -1;
		int empid = -1;
		String FirstName = "";
		String LastName = "";
		int reportstoid = -1;
		int reinallowance = -1;
		String email = "";
		String address = "";
		int am = -1;
		String sql = "select pass,username,role_id,dept_id,emp_id,first_name,last_name,reports_to_id,email,rein_allowance,address,approvedammount from employee ";

		try {

			 cont = ConnectionUtil.getConnection();

			PreparedStatement ps = cont.prepareStatement(sql);
			// ps.setString(1, user);
			// ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				pass = rs.getString(1);
				s = rs.getString(2);
				roleid = rs.getInt(3);
				deptid = rs.getInt(4);
				empid = rs.getInt(5);
				FirstName = rs.getString(6);
				LastName = rs.getString(7);
				reportstoid = rs.getInt(8);
				email = rs.getString(9);
				reinallowance = rs.getInt(10);
				address = rs.getString(11);
				am = rs.getInt(12);

				try
				{
					
				if(SignedInEmployee.empID == empid)
				{
					
					
					
				}
				}
				catch(Exception e)
				{
					
					
				}			
				// deptid = rs.getInt(6);
					
				Employees en = new Employees(FirstName, LastName, address, reportstoid, deptid, roleid, empid, s, pass, email, reinallowance,am);
				Collecters.emp.add(en);
				System.out.println(en.toString());

			}


		} catch (Exception e) {
			System.out.println("FAILURE");

			System.out.println(e.getMessage());
		}
		try {
			cont.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void pullAllReinDown() {
		int EMP_ID = 0;
		String COR_LOCATION = null;
		String APP_DATE = null;
		String COR_STARTDATE = null;
		String COR_ENDDATE = null;
		String COR_TIME = null;
		int REIN_ID = 0;
		int APP_ID = 0;
		int COR_ID = 0;
		int GRADE_ID = 0;
		int coursecost = 0;
		String GRADE = null;

		try {

			 cont = ConnectionUtil.getConnection();

			PreparedStatement ps = cont.prepareStatement("select * from Reinbursment");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				System.out.println("TESTING REINBURSMENTS");
				EMP_ID = rs.getInt(1);
				
				System.out.println(EMP_ID);
				COR_LOCATION = rs.getString(2);
				APP_DATE = rs.getString(3);
				COR_STARTDATE = rs.getString(4);
				COR_ENDDATE = rs.getString(5);
				COR_TIME = rs.getString(6);
				REIN_ID = rs.getInt(7);
				APP_ID = rs.getInt(8);
				COR_ID = rs.getInt(9);
				GRADE_ID = rs.getInt(10);
				GRADE = rs.getString(11);
				coursecost = rs.getInt(12);
				Reinbursment rein = new Reinbursment(EMP_ID, COR_LOCATION, APP_DATE, COR_STARTDATE, COR_ENDDATE,
						COR_TIME, REIN_ID, APP_ID, COR_ID, GRADE_ID, GRADE,coursecost);
				Collecters.rein.add(rein);
				System.out.println(rein.toString());
			
			}

		} catch (Exception e)

		{
			
System.out.println(e.getMessage());
		}
		
		try {
			cont.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    public void submitReinbursment(String startdate, String enddate,int totalcoursetime,String location, String description, int Cost, String justify,int eventtype, int gradetype, String approvalemail , String appdate)
    
    {
    	try{
    	 cont = ConnectionUtil.getConnection();
    	PreparedStatement ps = cont.prepareStatement("INSERT INTO REINBURSMENT(EMP_ID, COR_LOCATION, APP_DATE, COR_STARTDATE, COR_ENDDATE, COR_TIME, APP_ID, COR_ID, GRADE_ID,COURSECOST )VALUES (?, ?, ?, ?, ?, ?,? ,? ,?,? )");
         ps.setInt(1, Employee.SignedInEmployee.empID);
         ps.setString(2, location);
         ps.setString(3, appdate);
         ps.setString(4, startdate);
         ps.setString(5, enddate);
         ps.setInt(6, totalcoursetime);
         ps.setInt(7, 2);
         ps.setInt(8, eventtype );
         ps.setInt(9, gradetype);
         ps.setInt(10, Cost);
         ResultSet rs = ps.executeQuery();
         ;
    	}
    	catch(Exception e)
    	{
    		
    		System.out.println(e.getStackTrace());
    	System.out.println(e.getMessage() + " aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" );
    		
    	}
    	finally
    	{}
         
    	System.out.println(startdate+enddate+totalcoursetime+location+description+Cost+justify+eventtype+gradetype+approvalemail);
    	  Employee.Collecters.emp = new ArrayList<Employee.Employees>();
    	  Employee.Collecters.rein =new ArrayList<Employee.Reinbursment>();
    	  pullAllReinDown();
    	  pullAllEmpDown();
    	  try {
  			cont.close();
  		} catch (Exception e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
    	
    }
    
  
    
    
    public void pullAllMessages() {

		String Message = "";
		
	
		int empid = -1;
		
		int reinid = -1;
	
		int appid = -1;
		int other = 0;
		String sql = "select * from messages";

		try {

			 cont = ConnectionUtil.getConnection();

			PreparedStatement ps = cont.prepareStatement(sql);
			// ps.setString(1, user);
			// ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Message = rs.getString(1);
				empid = rs.getInt(2);
				reinid = rs.getInt(3);
				appid = rs.getInt(4);
				other = rs.getInt(5);
				

				try
				{
					
				if(SignedInEmployee.empID == empid)
				{
					
					
					
				}
				}
				catch(Exception e)
				{
					
					
				}			
				// deptid = rs.getInt(6);
					
				Messages msg = new Messages(Message, empid,appid ,reinid );
				Collecters.msgs.add(msg);
				msg.toString();
			//	System.out.println(en.toString());

			}


		} catch (Exception e) {
			System.out.println("FAILURE");

			System.out.println(e.getMessage());
		}
		try {
			cont.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
    
    
    
}
