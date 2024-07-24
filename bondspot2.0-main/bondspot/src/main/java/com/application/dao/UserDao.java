package com.application.dao;

import com.application.entity.User;

public interface UserDao {
	
	User findByUserName(String userName);

}
