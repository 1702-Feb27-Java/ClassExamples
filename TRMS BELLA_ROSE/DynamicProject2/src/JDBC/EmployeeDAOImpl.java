package JDBC;

import java.util.LinkedList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import Classes.Employee;

public class EmployeeDAOImpl implements EmployeeDAO
{

	private Connection conn;
	private Statement stmt;
	private PreparedStatement preStmt;
	private ResultSet rs;

	@Override
	public void insertEmployees(Employee per)
	{
		// TODO Auto-generated method stub
				try
				{
					conn = ConnectionFactory.getConnection();

					String query = "Insert into Employee (first_name, last_name, address, dept_id, reportsto, role_id, username, pw, email, allowance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					preStmt = conn.prepareStatement(query);

					String fName = per.getFirstName(), lName = per.getLastName(), uName = per.getUsername(), pw = per.getPassword(), address = per.getAddress(), email = per.getEmail();
					Integer type = per.getRoleId(), dept = per.getDeptId(), reportsTo = per.getDirSuperId();
					Float allowance = per.getCurrentAllowance();
					//System.out.println(fName + lName + uName + pw + type);
					// Fill out Statement Parameters
					preStmt.setString(1, fName);
					preStmt.setString(2, lName);
					preStmt.setString(3, address);
					preStmt.setInt(4, dept);
					preStmt.setInt(5, reportsTo);
					preStmt.setInt(6, type);
					preStmt.setString(7, uName);
					preStmt.setString(8, pw);
					preStmt.setString(9, email);
					preStmt.setFloat(10, allowance);
					

					// Execute the Query
					preStmt.executeUpdate();

				} catch (Exception ex)
				{
					ex.printStackTrace();
				} finally
				{
					DAOUtil.close(conn);
					DAOUtil.close(preStmt);
					DAOUtil.close(rs);
				}

	}

	@Override
	public LinkedList<Employee> getAllEmployees()
	{
		// TODO Auto-generated method stub
				try
				{
					conn = ConnectionFactory.getConnection();

					String query = "Select * from Employee";
					stmt = conn.createStatement();

					// Execute the Query
					rs = stmt.executeQuery(query);
					
					LinkedList<Employee> emps = new LinkedList<>();

					while (rs.next())
					{
						
						Employee temp = new Employee();
						
						temp.setEmpId(rs.getInt("employee_id"));
						temp.setDeptId(rs.getInt("dept_id"));
						temp.setRoleId(rs.getInt("role_id"));
						temp.setDirSuperId(rs.getInt("reportsto"));
						temp.setFirstName(rs.getString("first_name"));
						temp.setLastName(rs.getString("last_name"));
						temp.setAddress(rs.getString("address"));
						temp.setUsername(rs.getString("username"));
						temp.setPassword(rs.getString("password"));
						temp.setEmail(rs.getString("email"));
						temp.setCurrentAllowance(rs.getFloat("allowance"));
						
						emps.add(temp);
						
					}
					return emps;

					
				} catch (

				Exception ex)
				{
					ex.printStackTrace();
				} finally
				{
					DAOUtil.close(conn);
					DAOUtil.close(preStmt);
					DAOUtil.close(rs);
				}
				
				return null;
	}

	@Override
	public LinkedList<Employee> getEmployeesByRole(int role)
	{
		// TODO Auto-generated method stub
		try
		{
			conn = ConnectionFactory.getConnection();

			String query = "Select * from Employee where role_id = ?";
			preStmt = conn.prepareStatement(query);

			preStmt.setInt(1, role);

			// Execute the Query
			rs = preStmt.executeQuery();
			
			LinkedList<Employee> emps = new LinkedList<>();

			while (rs.next())
			{
				
				Employee temp = new Employee();
				
				temp.setEmpId(rs.getInt("employee_id"));
				temp.setDeptId(rs.getInt("dept_id"));
				temp.setRoleId(rs.getInt("role_id"));
				temp.setDirSuperId(rs.getInt("reportsto"));
				temp.setFirstName(rs.getString("first_name"));
				temp.setLastName(rs.getString("last_name"));
				temp.setAddress(rs.getString("address"));
				temp.setUsername(rs.getString("username"));
				temp.setPassword(rs.getString("password"));
				temp.setEmail(rs.getString("email"));
				temp.setCurrentAllowance(rs.getFloat("allowance"));
				
				emps.add(temp);
				
			}
			return emps;

			
		} catch (

		Exception ex)
		{
			ex.printStackTrace();
		} finally
		{
			DAOUtil.close(conn);
			DAOUtil.close(preStmt);
			DAOUtil.close(rs);
		}
		
		return null;

	}

	@Override
	public Employee getEmployeeById(int id)
	{
		// TODO Auto-generated method stub
				try
				{
					conn = ConnectionFactory.getConnection();

					String query = "Select * from Employee where employee_id = ?";
					preStmt = conn.prepareStatement(query);

					preStmt.setInt(1, id);

					// Execute the Query
					rs = preStmt.executeQuery();

					Employee temp = null;

					if (rs.next())
					{
						
						temp = new Employee();
						
						temp.setEmpId(rs.getInt("employee_id"));
						temp.setDeptId(rs.getInt("dept_id"));
						temp.setRoleId(rs.getInt("role_id"));
						temp.setDirSuperId(rs.getInt("reportsto"));
						temp.setFirstName(rs.getString("first_name"));
						temp.setLastName(rs.getString("last_name"));
						temp.setAddress(rs.getString("address"));
						temp.setUsername(rs.getString("username"));
						temp.setPassword(rs.getString("password"));
						temp.setEmail(rs.getString("email"));
						temp.setCurrentAllowance(rs.getFloat("allowance"));

						return temp;

					}

				} catch (

				Exception ex)
				{
					ex.printStackTrace();
				} finally
				{
					DAOUtil.close(conn);
					DAOUtil.close(preStmt);
					DAOUtil.close(rs);
				}

				return null;
	}

	@Override
	public Employee getEmployeeByUsername(String username)
	{
		// TODO Auto-generated method stub
		try
		{
			conn = ConnectionFactory.getConnection();

			String query = "Select * from Employee where username = ?";
			preStmt = conn.prepareStatement(query);

			preStmt.setString(1, username);

			// Execute the Query
			rs = preStmt.executeQuery();

			Employee temp = null;

			if (rs.next())
			{
				
				temp = new Employee();
				
				temp.setEmpId(rs.getInt("employee_id"));
				temp.setDeptId(rs.getInt("dept_id"));
				temp.setRoleId(rs.getInt("role_id"));
				temp.setDirSuperId(rs.getInt("reportsto"));
				temp.setFirstName(rs.getString("first_name"));
				temp.setLastName(rs.getString("last_name"));
				temp.setAddress(rs.getString("address"));
				temp.setUsername(rs.getString("username"));
				temp.setPassword(rs.getString("password"));
				temp.setEmail(rs.getString("email"));
				temp.setCurrentAllowance(rs.getFloat("allowance"));

				return temp;

			}

		} catch (

		Exception ex)
		{
			ex.printStackTrace();
		} finally
		{
			DAOUtil.close(conn);
			DAOUtil.close(preStmt);
			DAOUtil.close(rs);
		}

		return null;
	}

	@Override
	public void saveEmployee(Employee per)
	{
		// TODO Auto-generated method stub
		try
		{
			conn = ConnectionFactory.getConnection();

			String query = "Update Employee set first_name = ?, last_name = ?, address = ?, dept_id = ?, reportsto = ?, role_id = ?, username = ?, pw = ?, email = ?, allowance = ? where employee_id = ?";
			preStmt = conn.prepareStatement(query);

			String fName = per.getFirstName(), lName = per.getLastName(), uName = per.getUsername(), pw = per.getPassword(), address = per.getAddress(), email = per.getEmail();
			Integer type = per.getRoleId(), dept = per.getDeptId(), reportsTo = per.getDirSuperId();
			Float allowance = per.getCurrentAllowance();
			//System.out.println(fName + lName + uName + pw + type);
			// Fill out Statement Parameters
			preStmt.setString(1, fName);
			preStmt.setString(2, lName);
			preStmt.setString(3, address);
			preStmt.setInt(4, dept);
			preStmt.setInt(5, reportsTo);
			preStmt.setInt(6, type);
			preStmt.setString(7, uName);
			preStmt.setString(8, pw);
			preStmt.setString(9, email);
			preStmt.setFloat(10, allowance);
			preStmt.setInt(11, per.getEmpId());

			// Execute the Query
			preStmt.executeUpdate();

		} catch (Exception ex)
		{
			ex.printStackTrace();
		} finally
		{
			DAOUtil.close(conn);
			DAOUtil.close(preStmt);
			DAOUtil.close(rs);
		}

	}

	@Override
	public void deleteEmployeeById(int id)
	{
		// TODO Auto-generated method stub

				try
				{
					conn = ConnectionFactory.getConnection();

					String query = "Delete from Employee where employee_id = ?";
					preStmt = conn.prepareStatement(query);

					// Fill out Statement Parameters
					preStmt.setInt(1, id);

					// Execute the Query
					preStmt.executeUpdate();

				} catch (Exception ex)
				{
					ex.printStackTrace();
				} finally
				{
					DAOUtil.close(conn);
					DAOUtil.close(preStmt);
					DAOUtil.close(rs);
				}

	}

}
