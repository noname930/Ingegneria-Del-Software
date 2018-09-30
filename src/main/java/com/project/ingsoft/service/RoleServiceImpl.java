package com.project.ingsoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ingsoft.model.Role;
import com.project.ingsoft.repository.RoleRepository;

@Service("RoleService")
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRep;
	
	@Override
	public Role findbyRole(String Role) {
		// TODO Auto-generated method stub
		return roleRep.findByRole(Role);
	}

}
