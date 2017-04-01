package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static SessionFactory sessionFac = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	
	public static Session getSession(){
		
		return sessionFac.openSession();
	}

}
