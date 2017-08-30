package com.springjpa.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springjpa.auth.entity.Ville;

public interface VilleRepository extends JpaRepository<Ville, Long> {
	
	public List<Ville> findByDepartementOrderByNom(String departement);
	
	public Ville findBySlug (String nomCommune);
	
	

}
