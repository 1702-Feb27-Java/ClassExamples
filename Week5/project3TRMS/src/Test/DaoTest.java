package Test;

import java.util.ArrayList;

import DAO.DAOimp;
import User.appinfo;

public class DaoTest {

	public static void main(String[] args) {

		DAOimp test1 = new DAOimp();
		ArrayList<appinfo> list = new ArrayList<>();
		list = test1.getAllInfoBenCo();
		System.out.println(list);
		
		
	}

}
