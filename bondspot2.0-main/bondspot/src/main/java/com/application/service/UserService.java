package com.application.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.application.entity.User;

public interface UserService extends UserDetailsService{
	
	List<String> findAll();
	
	User findById(int id);
	
	User save(User user);
	
	void deleteById(int id);
	
	public User findByUserName(String userName);

	UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException;
	
	
	
	

}
