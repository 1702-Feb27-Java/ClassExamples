package app;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import oracle.jdbc.OracleTypes;



public class Demo {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		System.out.println("Hello");
		Connection conn  = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try{
			conn = ConnectionFactory.getInstance().getConnection();
			
			/*//create a new user
			sql = "{call createUser(?,?)}";
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, 301);
			cstmt.setString(2, "Bob");
			cstmt.executeUpdate();*/
			
			
			//Update a user
			sql = "{call updateUser(?,?)}";
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, 12);
			cstmt.setString(2, "New Spud");
			cstmt.executeUpdate();
			
			
			//Retrieve a user as a table row
			sql = "{call retrieveUser2(?,?,?)}";
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, 12);
			cstmt.registerOutParameter(2,java.sql.Types.INTEGER);
			cstmt.registerOutParameter(3,java.sql.Types.VARCHAR);
			cstmt.executeQuery();
			
			System.out.println(cstmt.getString(2) +" "+cstmt.getString(3));
			
			
			
			//Retrieve a user using a cursor and a ResultSet
			sql = "{call retrieveUser(?,?)}";
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, 12);
			cstmt.registerOutParameter(2,OracleTypes.CURSOR);
			cstmt.executeQuery();
			
			rs = (ResultSet) cstmt.getObject(2);
			rs.next();		
			System.out.println(rs.getString(1) +" "+rs.getString(2));
			
			
		}catch(Exception ex){
			ex.printStackTrace();
				
		}
		finally{
			try{rs.close();} catch(Exception e){/*ignored*/}
			try{cstmt.close();} catch(Exception e){/*ignored*/}
			try{conn.close();} catch(Exception e){/*ignored*/}
		}
		
		
		
		
	}

}
