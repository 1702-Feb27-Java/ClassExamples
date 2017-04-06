package com.revature.driver;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.revature.beans.Bear;
import com.revature.util.HibernateUtil;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Session sess = HibernateUtil.getSession();
		Query q = sess.createSQLQuery("SELECT * FROM BEAR WHERE BEAR_ID = ?").addEntity(Bear.class);
		List bList = q.setInteger(0, 1800).list();
		
		System.out.println((Bear)bList.get(0));
		
		
		/*BearDao dao = new BearDaoImpl();

		// dao.createBear();

		// dao.getOrLoad(50);

		// dao.queryDemo();

		Bear b = dao.getBearById(50);

		System.out.println(b);

		b = dao.getBearById(50);

		System.out.println(b);

		try {

			Thread.sleep(10000);

		} catch (Exception e) {

			System.out.println("oops");

		}

		b = dao.getBearById(50);

		System.out.println(b);
		
		System.out.println("end");*/

	}

	/*
	 * Session sess = HibernateUtil.getSession(); HoneyPot hp = (HoneyPot)
	 * sess.get(HoneyPot.class, 50);
	 * 
	 * System.out.println("Honey pot id" + hp.getHoneyPotId());
	 * System.out.println("Honey pot amount" + hp.getHoneyAmount());
	 * System.out.println("Honey pot volumn" + hp.getVolume());
	 * 
	 * int caveId = 50;
	 * 
	 * Cave c = (Cave) sess.get(Cave.class, caveId);
	 * 
	 * System.out.println(c);
	 * 
	 * for (Bear b : c.getBears()){
	 * 
	 * System.out.println(b);
	 * 
	 * }
	 * 
	 * sess.close();
	 */

}
