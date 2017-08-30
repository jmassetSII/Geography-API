package com.springjpa.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springjpa.auth.entity.Departement;

public interface DepartementRepository extends JpaRepository<Departement, Long> {
	
	@Query("select d from Departement d order by d.nomUppercase")
	List<Departement> getDepartements();
	
	
	

}
