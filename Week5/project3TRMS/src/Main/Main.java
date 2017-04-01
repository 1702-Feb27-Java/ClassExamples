package Main;

import DAO.DAOimp;
import User.UserInfo;

public class Main {
	/*
	 * public static void main(String args []){ UserInfo user = new UserInfo();
	 * DAOimp dao = new DAOimp(); user = dao.getUserInfo("123");
	 * System.out.println(user.getPassword().toString()); }
	 */

	public static boolean validLogin(UserInfo user, String pwd) {

		// do stuff

		// call dao
		// (new DAOimp()).getUserInfo(user);
		System.out.println("THIS IS MAIN " + user.getEmail() + pwd);
		// determine
		System.out.println(user.getPassword());
		if (user.getPassword().equals(pwd)) {
			return true;
		}

		return false;
	}
}
