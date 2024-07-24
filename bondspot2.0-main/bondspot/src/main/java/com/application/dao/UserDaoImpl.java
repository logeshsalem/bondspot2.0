package com.application.dao;

import org.springframework.stereotype.Repository;

import com.application.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class UserDaoImpl implements UserDao{
	
	private EntityManager entityManager;
	
	public UserDaoImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public User findByUserName(String userName) {
		//retrieve from database using username
		TypedQuery<User> theQuery = entityManager.createQuery("from User where username=:uName", User.class);
		theQuery.setParameter("uName", userName);
		
		User theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		}catch (Exception e) {
			theUser = null;
		}
		
		return theUser;
	}

}
