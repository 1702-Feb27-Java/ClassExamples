package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import Users.*;

public class PersonDaoImpl implements PersonDAO
{

	private Connection conn;
	private Statement stmt;
	private PreparedStatement preStmt;
	private ResultSet rs;

	@Override
	public void insertPerson(Person per)
	{
		// TODO Auto-generated method stub
		
		try
		{
			conn = ConnectionFactory.getConnection();

			String query = "Insert into users (user_id, first_name, last_name, username, pw, role_id, resolver_id) VALUES (null, ?, ?, ?, ?, ?, null)";
			preStmt = conn.prepareStatement(query);
			
			//Fill out Statement Parameters
			preStmt.setString(1, per.getFirstName());
			preStmt.setString(2, per.getLastName());
			preStmt.setString(3, per.getUsername());
			preStmt.setString(4, per.getPassword());
			preStmt.setInt(5, per.getType());

			// Execute the Query
			rs = stmt.executeQuery(query);

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
	public LinkedList<Person> getAllPersons()
	{
		// TODO Auto-generated method stub

		LinkedList<Person> persons = new LinkedList<Person>();

		try
		{
			conn = ConnectionFactory.getConnection();

			String query = "Select Users.user_id as Users_ID, Users.first_name as Users_F_Name, Users.last_name as Users_L_Name, Users.username as Users_UName, Users.pw as Users_Password, Users.role_id as Users_Role_Id, Banker.user_id as Banker_ID, Banker.first_name as Bankerv_F_Name, Banker.last_name as Banker_L_Name, Banker.username as Banker_UName, Banker.pw as Banker_Password, Banker.role_id as Banker_Role_Id from Users Banker right join User Usersomer on Users.resolver_id = Banker.UserId";
			stmt = conn.createStatement();

			// Execute the Query
			rs = stmt.executeQuery(query);

			while (rs.next())
			{
				Person temp = new Person();

				temp.setUserId(rs.getInt("Users_ID"));
				temp.setFirstName(rs.getString("Users_F_Name"));
				temp.setLastName(rs.getString("Users_L_Name"));
				temp.setUsername(rs.getString("Users_UName"));
				temp.setPassword(rs.getString("Users_Password"));
				temp.setType(rs.getInt("Users_Role_Id"));

				if (rs.getInt("Users_Role_Id") == 1)
				{
					persons.add((Admin) temp);
				} else if (rs.getInt("Users_Role_Id") == 2)
				{
					persons.add((Employee) temp);
				} else
				{
					///// banker stuff
					Customer user = new Customer(rs.getInt("Users_ID"), rs.getString("Users_F_Name"),
							rs.getString("Users_L_Name"), rs.getString("Users_UName"), rs.getString("Users_Password"));
					Employee banker = new Employee();

					banker.setUserId(rs.getInt("Banker_ID"));
					banker.setFirstName(rs.getString("Banker_F_Name"));
					banker.setLastName(rs.getString("Banker_L_Name"));
					banker.setUsername(rs.getString("Banker_UName"));
					banker.setPassword(rs.getString("Banker_Password"));
					banker.setType(rs.getInt("Banker_Role_Id"));

					user.setBanker(banker);

					temp = user;

					persons.add(temp);
				}

			}

		} catch (Exception ex)
		{
			ex.printStackTrace();
		} finally
		{
			DAOUtil.close(conn);
			DAOUtil.close(stmt);
			DAOUtil.close(rs);
		}
		return persons;
	}

	@Override
	public LinkedList<Person> getPersonsByRole(int role)
	{
		// TODO Auto-generated method stub

		LinkedList<Person> persons = new LinkedList<Person>();

		try
		{
			conn = ConnectionFactory.getConnection();

			String query = "Select Users.user_id as User_ID, Users.first_name as User_F_Name, Users.last_name as User_L_Name, Users.username as User_UName, Users.pw as User_Password, Users.role_id as User_Role_Id, Banker.user_id as Banker_ID, Banker.first_name as Bankerv_F_Name, Banker.last_name as Banker_L_Name, Banker.username as Banker_UName, Banker.pw as Banker_Password, Banker.role_id as Banker_Role_Id from Users Banker right join User Useromer on Users.resolver_id = Banker.UserId";
			stmt = conn.createStatement();

			// Execute the Query
			rs = stmt.executeQuery(query);

			while (rs.next())
			{
				Person temp = new Person();

				temp.setUserId(rs.getInt("User_ID"));
				temp.setFirstName(rs.getString("User_F_Name"));
				temp.setLastName(rs.getString("User_L_Name"));
				temp.setUsername(rs.getString("User_UName"));
				temp.setPassword(rs.getString("User_Password"));
				temp.setType(rs.getInt("User_Role_Id"));

				if (rs.getInt("User_Role_Id") == role)
				{
					if (rs.getInt("User_Role_Id") == 1)
					{
						persons.add((Admin) temp);
					} else if (rs.getInt("User_Role_Id") == 2)
					{
						persons.add((Employee) temp);
					} else
					{
						///// banker stuff
						Customer user = new Customer(rs.getInt("User_ID"), rs.getString("User_F_Name"),
								rs.getString("User_L_Name"), rs.getString("User_UName"), rs.getString("User_Password"));
						Employee banker = new Employee();

						banker.setUserId(rs.getInt("Banker_ID"));
						banker.setFirstName(rs.getString("Banker_F_Name"));
						banker.setLastName(rs.getString("Banker_L_Name"));
						banker.setUsername(rs.getString("Banker_UName"));
						banker.setPassword(rs.getString("Banker_Password"));
						banker.setType(rs.getInt("Banker_Role_Id"));

						user.setBanker(banker);

						temp = user;

						persons.add(temp);
					}
				}

			}

		} catch (Exception ex)
		{
			ex.printStackTrace();
		} finally
		{
			DAOUtil.close(conn);
			DAOUtil.close(stmt);
			DAOUtil.close(rs);
		}
		return persons;

	}

	@Override
	public Person getPersonById(int id)
	{
		// TODO Auto-generated method stub
		Person pers = new Person();

		try
		{
			conn = ConnectionFactory.getConnection();

			String query = "Select Users.user_id as User_ID, Users.first_name as User_F_Name, Users.last_name as User_L_Name, Users.username as User_UName, Users.pw as User_Password, Users.role_id as User_Role_Id, Banker.user_id as Banker_ID, Banker.first_name as Bankerv_F_Name, Banker.last_name as Banker_L_Name, Banker.username as Banker_UName, Banker.pw as Banker_Password, Banker.role_id as Banker_Role_Id from Users Banker right join User Useromer on Users.resolver_id = Banker.UserId";
			stmt = conn.createStatement();

			// Execute the Query
			rs = stmt.executeQuery(query);

			Person temp = new Person();

			while (rs.next())
			{
				if (rs.getInt("Cust_ID") != id)
					continue;

				temp.setUserId(rs.getInt("Cust_ID"));
				temp.setFirstName(rs.getString("Cust_F_Name"));
				temp.setLastName(rs.getString("Cust_L_Name"));
				temp.setUsername(rs.getString("Cust_UName"));
				temp.setPassword(rs.getString("Cust_Password"));
				temp.setType(rs.getInt("Cust_Role_Id"));

				if (rs.getInt("Cust_ID") == 1)
				{
					pers = ((Admin) temp);
				} else if (rs.getInt("Cust_Role_Id") == 2)
				{
					pers = ((Employee) temp);
				} else
				{
					Customer user = new Customer(rs.getInt("Cust_ID"), rs.getString("Cust_F_Name"),
							rs.getString("Cust_L_Name"), rs.getString("Cust_UName"), rs.getString("Cust_Password"));
					Employee banker = new Employee();

					///// banker stuff
					banker.setUserId(rs.getInt("Banker_ID"));
					banker.setFirstName(rs.getString("Banker_F_Name"));
					banker.setLastName(rs.getString("Banker_L_Name"));
					banker.setUsername(rs.getString("Banker_UName"));
					banker.setPassword(rs.getString("Banker_Password"));
					banker.setType(rs.getInt("Banker_Role_Id"));

					user.setBanker(banker);

					temp = user;

				}

			}

		} catch (Exception ex)
		{
			ex.printStackTrace();
		} finally
		{
			DAOUtil.close(conn);
			DAOUtil.close(stmt);
			DAOUtil.close(rs);
		}
		return pers;
	}

	@Override
	public Person getPersonByUsername(String username)
	{
		// TODO Auto-generated method stub
				Person pers = new Person();

				try
				{
					conn = ConnectionFactory.getConnection();

					String query = "Select Users.user_id as User_ID, Users.first_name as User_F_Name, Users.last_name as User_L_Name, Users.username as User_UName, Users.pw as User_Password, Users.role_id as User_Role_Id, Banker.user_id as Banker_ID, Banker.first_name as Bankerv_F_Name, Banker.last_name as Banker_L_Name, Banker.username as Banker_UName, Banker.pw as Banker_Password, Banker.role_id as Banker_Role_Id from Users Banker right join User Useromer on Users.resolver_id = Banker.UserId";
					stmt = conn.createStatement();

					// Execute the Query
					rs = stmt.executeQuery(query);

					Person temp = new Person();

					while (rs.next())
					{
						if (username.equals(rs.getInt("Cust_UName")) == false)
							continue;

						temp.setUserId(rs.getInt("Cust_ID"));
						temp.setFirstName(rs.getString("Cust_F_Name"));
						temp.setLastName(rs.getString("Cust_L_Name"));
						temp.setUsername(rs.getString("Cust_UName"));
						temp.setPassword(rs.getString("Cust_Password"));
						temp.setType(rs.getInt("Cust_Role_Id"));

						if (rs.getInt("Cust_ID") == 1)
						{
							pers = ((Admin) temp);
						} else if (rs.getInt("Cust_Role_Id") == 2)
						{
							pers = ((Employee) temp);
						} else
						{
							Customer user = new Customer(rs.getInt("Cust_ID"), rs.getString("Cust_F_Name"),
									rs.getString("Cust_L_Name"), rs.getString("Cust_UName"), rs.getString("Cust_Password"));
							Employee banker = new Employee();

							///// banker stuff
							banker.setUserId(rs.getInt("Banker_ID"));
							banker.setFirstName(rs.getString("Banker_F_Name"));
							banker.setLastName(rs.getString("Banker_L_Name"));
							banker.setUsername(rs.getString("Banker_UName"));
							banker.setPassword(rs.getString("Banker_Password"));
							banker.setType(rs.getInt("Banker_Role_Id"));

							user.setBanker(banker);

							temp = user;

						}

					}

				} catch (Exception ex)
				{
					ex.printStackTrace();
				} finally
				{
					DAOUtil.close(conn);
					DAOUtil.close(stmt);
					DAOUtil.close(rs);
				}
				return pers;
	}

	@Override
	public void savePerson(Person per)
	{
		// TODO Auto-generated method stub
		
		try
		{
			conn = ConnectionFactory.getConnection();

			String query = "Update users set first_name = ?, last_name = ?, username = ?, pw = ?, resolver_id = ? where user_id = ?";
			preStmt = conn.prepareStatement(query);
			
			//Fill out Statement Parameters
			preStmt.setString(1, per.getFirstName());
			preStmt.setString(2, per.getLastName());
			preStmt.setString(3, per.getUsername());
			preStmt.setString(4, per.getPassword());
			
			Integer resolverId = null;
			
			if (per.getType() == 3 && ((Customer)per).getBanker() != null)
			{
				resolverId = ((Customer)per).getBanker().getUserId();
			}
			
			preStmt.setInt(5, resolverId);
			
			preStmt.setInt(6, per.getUserId());

			// Execute the Query
			rs = stmt.executeQuery(query);

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
	public void deletePersonById(int id)
	{
		// TODO Auto-generated method stub
		
		try
		{
			conn = ConnectionFactory.getConnection();

			String query = "Delete from users where user_id = ?";
			preStmt = conn.prepareStatement(query);
			
			//Fill out Statement Parameters
			preStmt.setInt(1, id);

			// Execute the Query
			rs = stmt.executeQuery(query);

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
