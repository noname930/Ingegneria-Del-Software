package com.project.ingsoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.ingsoft.model.Acquisto;

@Repository("AcquistoRepository")
public interface AcquistoRepository extends JpaRepository<Acquisto,Integer>{
	
	@Query("SELECT c FROM Acquisto c WHERE c.user_id=?1")
	public List<Acquisto> findbyUserId(Integer user_id);

}
