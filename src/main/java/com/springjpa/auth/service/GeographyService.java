package com.springjpa.auth.service;

import java.util.List;

import com.springjpa.auth.dto.DepartementDto;
import com.springjpa.auth.dto.VilleDto;

public interface GeographyService {
	
	List<DepartementDto> getDepartements();
	
	DepartementDto getDepartement(Long id);
	
	List<VilleDto> getVilles(String departement);
	
	VilleDto getDetailVille(Long id);

}
