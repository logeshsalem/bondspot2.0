package com.application.dao;

import org.springframework.stereotype.Repository;

import com.application.entity.Role;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class RoleDaoImpl implements RoleDao{
	
	private EntityManager entityManager;
	
	public RoleDaoImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public Role findRoleByName(String theRoleName) {
		
		//retrieve from database using name
		TypedQuery<Role> theQuery = entityManager.createQuery("from Role where name=:roleName",Role.class);
		theQuery.setParameter("roleName", theRoleName);
		
		Role theRole = null;
		
		try {
			theRole = theQuery.getSingleResult();
		}catch (Exception e) {
			theRole = null;
		}
		
		return theRole;
	}
	
	
	
}
