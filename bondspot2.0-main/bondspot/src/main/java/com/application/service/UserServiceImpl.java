package com.application.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.application.dao.RoleDao;
import com.application.dao.UserDao;
import com.application.entity.Role;
import com.application.entity.User;
import com.application.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Service
public class UserServiceImpl implements UserService{
	
	
	
	private UserRepository userRepository;	
	
	private EntityManager entityManager;
	
	private UserDao userDao;
	
	private RoleDao roleDao;
	
	public UserServiceImpl(UserRepository theUserRepository, EntityManager theEntityManager,
			UserDao theUserDao, RoleDao theRoleDao) {
					userRepository = theUserRepository;		
					entityManager = theEntityManager;
					userDao = theUserDao;
					roleDao = theRoleDao;
	}	
	

	@Override
	public List<String> findAll() {		
		TypedQuery<String> query = entityManager.createQuery(
			"SELECT CONCAT(' id: ', u.id,"
						+ "' email: ', u.username,"
						+ "' passwords: ', u.passwords,"
						+ "' firstName: ', u.firstName,"
						+ "' lastName: ', u.lastName,"
						+ "' dob: ', u.dob,"
						+ "' gender: ', u.gender,"
						+ "' phoneNumber: ', u.phoneNumber,"
						+ "' address: ', u.address,"
						+ "' registrationDate: ', u.registrationDate)"
						+ "From User u ",String.class);	
		
		return query.getResultList();
	}
	
	

	@Override
	public User findById(int theId) {
		//create query
		TypedQuery<User> query = entityManager.createQuery(						
		"SELECT new com.application.entity.User(u.id, u.username, u.passwords, u.firstName, u.lastName, "
		+ "u.dob, u.gender, u.phoneNumber, u.address, u.registrationDate) " +
		"FROM User u WHERE u.id = :id", User.class);
						
		query.setParameter("id", theId);
						
		//execute query
		return  query.getSingleResult();		
	}
	

	@Override
	public User save(User user) {
		User saveUser = userRepository.save(user);		
		return saveUser;
		
	}

	@Override
	public void deleteById(int id) {
		userRepository.deleteById(id);		
	}


	@Override
	public User findByUserName(String userName) {
		//check the database if the user already exists
		return userDao.findByUserName(userName);
	}
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		if(user==null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPasswords(),
				mapRolesToAuthorities(user.getRoles()));
	}


	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}


	


	

	

}
