package app;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import beans.Bear;
import beans.Cave;
import beans.HoneyPot;
import util.HibernateUtil;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Session session = HibernateUtil.getSession();
		HoneyPot hp = (HoneyPot)session.get(HoneyPot.class, 50);
		System.out.println("id " + hp.getId() + " amount " + hp.getAmount() + " volume " + hp.getVolume());
		
		//Cave cav = (Cave)session.get(Cave.class, 3);
		
		Criteria cr = session.createCriteria(Bear.class);
		cr.add(Restrictions.gt("BEAR_ID", 3));
		List rs = cr.list();
		
		System.out.println(rs.toString());
		
	}

}
