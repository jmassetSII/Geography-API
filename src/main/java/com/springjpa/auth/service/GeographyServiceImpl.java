package com.springjpa.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springjpa.auth.dto.DepartementDto;
import com.springjpa.auth.dto.VilleDto;
import com.springjpa.auth.entity.Departement;
import com.springjpa.auth.entity.Ville;
import com.springjpa.auth.repository.DepartementRepository;
import com.springjpa.auth.repository.VilleRepository;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;


@Service
public class GeographyServiceImpl implements GeographyService{
	
	@Autowired
	private DepartementRepository departementRepository;
	
	@Autowired
	private VilleRepository villeRepository;
	
	private MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
	private MapperFacade mapper = mapperFactory.getMapperFacade();

	@Override
	@Transactional (readOnly = true)
	public List<DepartementDto> getDepartements() {
		List<Departement> listDepartements = departementRepository.getDepartements();
		List<DepartementDto> listDepartementsDto = new ArrayList<DepartementDto>();
		for (Departement departement : listDepartements) {
			
			listDepartementsDto.add(new DepartementDto(departement.getCode(), departement.getNomUppercase()));
		}
		
		return listDepartementsDto;
	}
	
	@Override
	@Transactional (readOnly = true)
	public DepartementDto getDepartement(Long id) {
		Departement departement = departementRepository.findOne(id);
		DepartementDto departementDto = mapper.map(departement, DepartementDto.class);
		
		return departementDto;
	}


	@Override
	@Transactional (readOnly = true)
	public List<VilleDto> getVilles(String departement) {
		List<Ville> listVilles = villeRepository.findByDepartementOrderByNom(departement);
		List<VilleDto> listVillesDto = new ArrayList<VilleDto>();
		for (Ville ville : listVilles) {
			
			listVillesDto.add(new VilleDto(ville.getId(), ville.getNom(), ville.getCodePostal(), ville.getPopulation_1999(), ville.getPopulation_2010(), ville.getPopulation_2012(), ville.getDepartement()));
		}
		return listVillesDto;
	}

	@Override
	@Transactional (readOnly = true)
	public VilleDto getDetailVille(Long villeId) {
		Ville ville = villeRepository.findOne(villeId);
		VilleDto villeDto = mapper.map(ville, VilleDto.class);
		
		return villeDto;
	}

}
