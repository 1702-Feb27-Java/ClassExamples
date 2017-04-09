/*package com.revature.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.domain.FlashCard;

@Transactional(readOnly=true, propagation=Propagation.REQUIRED, isolation=Isolation.SERIALIZABLE)
public class DaoImpl implements Dao {
	
	private SessionFactory sessionFactory;
	
	public DaoImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void saveFC(FlashCard fc) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<FlashCard> getAllFC() {
		// TODO Auto-generated method stub
		List<FlashCard> fcList = sessionFactory.getCurrentSession().createCriteria(FlashCard.class).list();
		return fcList;
	}

	@Override
	public FlashCard getFlashCardById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
*/