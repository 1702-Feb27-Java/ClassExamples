package com.revature.jdbc;

import java.sql.*;

public class StudentDAOImpl {

	Connection con = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;

	public void setup() {

		try {
			// 1. load and register driver class
			Class.forName("oracle.jdbc.OracleDriver");

			// 2. Create connection object
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "revature");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void prepareInsertStudent() {

		try {

			String insertSQL = "insert into student values(?,?,?)";
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setInt(1, 5);
			pstmt.setString(2, "Karan");
			pstmt.setInt(3, 22);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void insertStudent() {

		try {
			stmt = con.createStatement();

			String insertSQL = "insert into student values(4, 'Karan' , 29 )";

			stmt.executeUpdate(insertSQL);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void selectStudent() {

		try {
			stmt = con.createStatement();
			String sql = "select * from student";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				System.out.println(" id is: " + rs.getString(1));
				System.out.println(" name is: " + rs.getString(2));
				System.out.println(" age is: " + rs.getString(3));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void close() {

		try {
			con.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		StudentDAOImpl studObj = new StudentDAOImpl();
		studObj.setup();
		studObj.insertStudent();
		studObj.close();

	}

}
