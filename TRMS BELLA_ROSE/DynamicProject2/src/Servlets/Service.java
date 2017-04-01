package Servlets;

import java.sql.Date;
import java.text.ParseException;
import java.util.LinkedList;

import Classes.ChatMessage;
import Classes.Employee;
import Classes.Reimbursement;
import JDBC.ChatDAOImpl;
import JDBC.EmployeeDAOImpl;
import JDBC.ReimbursementDAOImpl;

public class Service
{

	EmployeeDAOImpl impl = new EmployeeDAOImpl();
	ReimbursementDAOImpl implRe = new ReimbursementDAOImpl();
	ChatDAOImpl implCh = new ChatDAOImpl();

	public boolean validLogin(String username, String password)
	{
		Employee temp = impl.getEmployeeByUsername(username);

		// Check to see if a employee was returned
		if (temp == null)
			return false;
		else // if so, check to see if password equals passed in password param
			return temp.getPassword().equals(password);
	}

	public Employee getCurrEmp(String username)
	{
		return impl.getEmployeeByUsername(username);
	}

	public boolean insertReim(Reimbursement reim, Employee requestor)
	{

		try
		{

			reim.setCourseLength(calcCourseLength(reim));
			reim.setReim_amnt(calcAmount(reim));
			reim.setApprovalId(calcInitialStage(requestor));
			
			return implRe.insertReimbursement(reim);
			

		} catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;

	}
	
	public void saveReim(Reimbursement reim)
	{
		implRe.saveReimbursement(reim);
	}

	private int calcCourseLength(Reimbursement reim) throws ParseException
	{
		java.util.Date d1 = reim.getEndDate();
		java.util.Date d2 = reim.getStartDate();
		

		long diff = Math.abs(d1.getTime() - d2.getTime());
		long diffDays = diff / (24 * 60 * 60 * 1000);

		return (int) diffDays;
	}

	private float calcAmount(Reimbursement reim)
	{
		float avail = (float) (1000 - implRe.getSumOfReim(reim.getEmpId()));
		float maxAmt = reim.getCourseCost() * implRe.getPercentageByCourseId(reim.getCourseId());

		if (maxAmt > avail)
		{
			return avail;
		}

		return maxAmt;
	}

	private int calcInitialStage(Employee requestor)
	{
		switch (requestor.getRoleId())
		{
		case 1:
			return 1;
		case 2:
			return 2;
		case 3:
			return 4;

		default:
			throw new RuntimeException("Initial Stage Switch Broke");
		}
	}

	public static java.util.Date convertFromSQLDateToJAVADate(java.sql.Date sqlDate)
	{
		java.util.Date javaDate = null;
		if (sqlDate != null)
		{
			javaDate = new Date(sqlDate.getTime());
		}
		return javaDate;
	}
	
	public LinkedList<Reimbursement> getReimsByEmpId(int id)
	{
		return implRe.getReimbursementsByEmployee_ID(id);
	}
	
	public Reimbursement getReimsByReimId(int id)
	{
		System.out.println("Service " + id);
		return implRe.getReimbursementByReimbursement_Id(id);
	}
	
	public void insertMessage(ChatMessage mess)
	{
		implCh.insertMessage(mess);
	}
	
	public Employee getEmpById(int id)
	{
		return impl.getEmployeeById(id);
	}
	
	public LinkedList<Reimbursement> getReimsByApprover(Employee emp)
	{
		System.out.println("Curr Emp Id" + emp.getEmpId());
		if (emp.getDept() == "BenCo")
		{
			if (emp.getRoleId() == 2)
			{
				implRe.getReimbursementsByApproval_ID(1);
			}
			else if (emp.getRoleId() == 3)
			{
				implRe.getReimbursementsByApproval_ID(2);
			}
			else if (emp.getRoleId() == 1) //Check if its not the person who owns the request
			{
				implRe.getReimbursementsByApproval_ID(4);
			}
			return null;
		}
		else ///////CHECK FOR SAME DEPT
		{
			LinkedList<Reimbursement> list = null;
			
			if (emp.getRoleId() == 2)
			{
				///// Ids: 1 = new		3 = Supervisor needs info
				list = implRe.getReimbursementsByApproval_ID(1);
				list.addAll(implRe.getReimbursementsByApproval_ID(3));
			}
			else if (emp.getRoleId() == 3)
			{
			///// Ids: 2 = Supervisor Approved	5 = DeptHead  needs info
				list = implRe.getReimbursementsByApproval_ID(2);
				list.addAll(implRe.getReimbursementsByApproval_ID(5));
			}
			
			for (int i = 0; i < list.size(); i++)
			{
				Employee owner = impl.getEmployeeById(list.get(i).getEmpId());
								
				if (owner.getDeptId() != emp.getDeptId())
				{
					list.remove(i);
					i--;
				}
			}
			
			return list;
		}
	}

}
