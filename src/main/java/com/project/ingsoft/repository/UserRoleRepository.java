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
	

	@Query("SELECT r FROM User_Role ur INNER JOIN Role r ON (ur.role_id=r.role_id) WHERE ur.user_id=?1")
	Role findRoleByUserID(Integer user_id);
}
