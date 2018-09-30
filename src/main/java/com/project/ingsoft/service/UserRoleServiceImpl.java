package com.project.ingsoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ingsoft.model.Role;
import com.project.ingsoft.model.User;
import com.project.ingsoft.model.User_Role;
import com.project.ingsoft.repository.UserRoleRepository;

@Service("UserRoleService")
public class UserRoleServiceImpl implements UserRoleService{
	
	@Autowired
	private UserRoleRepository urRepository;
	
	@Override
	public void insertAdminRole(User u, Role r,User_Role ur)
	{
		ur.setUser_id(u.getId());
		ur.setRole_id(r.getId());
		urRepository.save(ur);
	}
}
