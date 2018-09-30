package com.project.ingsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.ingsoft.model.Role;
import com.project.ingsoft.model.User;
import com.project.ingsoft.model.User_Role;

@Repository("UserRoleRepository")
public interface UserRoleRepository extends JpaRepository<User_Role,Integer> {
	
	//public void insertAdminRole(User u, Role r,User_Role ur);
}
