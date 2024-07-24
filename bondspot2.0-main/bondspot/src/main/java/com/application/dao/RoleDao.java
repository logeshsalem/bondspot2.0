package com.application.dao;

import com.application.entity.Role;

public interface RoleDao {
	
	public Role findRoleByName(String theRoleName);

}
