package com.springjpa.auth.ws.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;

import com.springjpa.auth.dto.DepartementDto;
import com.springjpa.auth.dto.VilleDto;
import com.springjpa.auth.service.GeographyService;
import com.springjpa.auth.ws.GeographyRestWS;

@WebService
public class GeographyRestWSImpl implements GeographyRestWS {
	
//	@Autowired
//	private GeographyService geographyService;
//
//	@Override
//	public List<DepartementDto> getListDepartements() {
//		List<DepartementDto> listDepartements = new ArrayList<>();
//		try {
//			listDepartements = geographyService.getDepartements();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return listDepartements;
//	}
//
//	@Override
//	public DepartementDto getDepartement(Long id) {
//		DepartementDto departement = geographyService.getDepartement(id);
//		if (null == departement) {
//			throw new NoResultException();
//		}
//		return departement;
//	}
//
//	@Override
//	public List<VilleDto> getListVilles(String departement) {
//		List<VilleDto> listVilles = new ArrayList<>();
//		try {
//			listVilles = geographyService.getVilles(departement);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return listVilles;
//	}
//
//	@Override
//	public VilleDto getVille(Long id) {
//		VilleDto ville = geographyService.getDetailVille(id);
//		if (null == ville) {
//			throw new NoResultException();
//		}
//		return ville;
//	}


}
