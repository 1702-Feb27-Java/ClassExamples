package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.beans.Bear;
import com.revature.beans.Cave;
import com.revature.beans.HoneyPot;
import com.revature.util.HibernateUtil;

public class BearDaoImpl implements BearDao {

	public Bear getBearById(int bearId) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
		
		Bear b = (Bear) session.get(Bear.class, bearId);
		
		session.close();
		return b;
	}

	public void setBear(Bear bear) {
		// TODO Auto-generated method stub

	}

	public List<Bear> getAllBears() {
		// TODO Auto-generated method stub
		return null;
	}

	public Cave getCaveById(int caveId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCave(Cave cave) {
		// TODO Auto-generated method stub

	}

	public List<Cave> getAllCaves() {
		// TODO Auto-generated method stub
		return null;
	}

	public HoneyPot getHoneyPotById(int honeyPotId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setHoneyPot(HoneyPot honeyPot) {
		// TODO Auto-generated method stub

	}

	public List<HoneyPot> getAllHoneyPots() {
		// TODO Auto-generated method stub
		return null;
	}

	public void createBear() {
		// TODO Auto-generated method stub

		Session session = HibernateUtil.getSession();

		Transaction tx = session.beginTransaction();

		// Roaming Bears Looking for a place to hibernate! zzz

		Bear parent = new Bear("brown", "Kodiac", 0, 700, new HoneyPot(100, 100));

		int i = (Integer) session.save(parent.getHoneyPot());

		System.out.println(i + " Parent Bear's ------------HoneyPot Generated ID");

		// Bear has little Cub
		Bear child = new Bear("brown", "Kociac", 0, 600, new HoneyPot(30.0, 30.0));

		parent.getChildren().add(child);

		session.save(child.getHoneyPot());

		// They found a cave
		Cave newHome = new Cave("dark cave", 600);

		parent.setBearHome(newHome);

		child.setBearHome(newHome);

		session.save(newHome);

		session.save(parent);

		session.save(child);

		session.save(parent);

		// parent.getHoneyPot().setBear(parent);

		System.out.println("Time To Hibernate -----");

		tx.commit();

	}

	public Bear getOrLoad(int id) {
		// TODO Auto-generated method stub

		Session session = HibernateUtil.getSession();

		Bear bearGet = (Bear) session.load(Bear.class, id);

		System.out.println(bearGet.getClass() + " : getBear");

		return bearGet;
	}

	@SuppressWarnings("unchecked")
	public void criteriaDemo() {

		Session session = HibernateUtil.getSession();

		Criteria criteria;

		System.out.println("------------List All Bears-------------");

		List<Bear> bears = session.createCriteria(Bear.class).list();

		for (Bear b : bears) {

			System.out.println(b);

		}

		System.out.println("-------------Only Heavy Bears-----------");

		bears = session.createCriteria(Bear.class).add(Restrictions.gt("weight", 900.0)).list();

		for (Bear b : bears) {

			System.out.println(b);

		}

		System.out.println("-----------------%--------------------");
		criteria = session.createCriteria(Bear.class)
				.add(Restrictions.and(
						Restrictions.ilike("bearColor", "black"), 
						Restrictions.isNotNull("height")));

		for (Bear b : bears) {

			System.out.println(b);

		}

	}

	public void queryDemo() {
		// TODO Auto-generated method stub
		System.out.println("--------------Get One Bear--------------");
		Session session = HibernateUtil.getSession();
		
		String hql = "FROM Bear AS b WHERE b.breed = :type";
		
		Query query = session.createQuery(hql);
		
		query.setParameter("type", "Grizzly Bear");
		
		List<Bear> bears = query.list();
		
		for(Bear b: bears){
			
			System.out.println(b);
			
		}
		
		System.out.println("---------------feed a bear-------------");
		
		hql = "UPDATE Bear SET weight = weight + :fat WHERE bearId = :bearId";
		
		query = session.createQuery(hql);
		
		Transaction t = session.beginTransaction();
		
		query.setParameter("fat", 400.0);
		query.setParameter("bearId", 200);
		
		query.executeUpdate();
		
		t.commit();
		
		Bear b = (Bear) session.get(Bear.class, 200);
		
		System.out.println(b);
		
		
	}

	public void testCache() {
		// TODO Auto-generated method stub
		
	}


}

