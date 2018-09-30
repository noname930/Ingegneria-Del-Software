package com.project.ingsoft.service;

import com.project.ingsoft.model.Role;
import com.project.ingsoft.model.User;
import com.project.ingsoft.model.User_Role;

public interface UserRoleService {
	public void insertAdminRole(User u, Role r,User_Role ur);
}
