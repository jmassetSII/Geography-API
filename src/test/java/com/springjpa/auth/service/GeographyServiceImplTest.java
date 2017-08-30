package com.springjpa.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springjpa.auth.entity.Departement;
import com.springjpa.auth.entity.Ville;
import com.springjpa.auth.repository.DepartementRepository;
import com.springjpa.auth.repository.VilleRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class GeographyServiceImplTest {
	
	@Configuration
	static class GeographyServiceTextContextConfiguration {
		@Bean
		public GeographyService geographyService() {
			return new GeographyServiceImpl();
		}
		@Bean
		public DepartementRepository departementRepository() {
			return Mockito.mock(DepartementRepository.class);
		}
		@Bean
		public VilleRepository villeRepository() {
			return Mockito.mock(VilleRepository.class);
		}
		
	}
	
	@Autowired
	private DepartementRepository departementRepository;
	
	@Autowired
	private VilleRepository villeRepository;
	

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testGetDepartements() {
		List<Departement> departements = new ArrayList<>();
		Mockito.when(departementRepository.findAll()).thenReturn(departements);
	}

	@Test
	public void testGetVilles() {
		String departement = "59";
		List<Ville> villes = new ArrayList<>();
		Mockito.when(villeRepository.findByDepartementOrderByNom(departement)).thenReturn(villes);
	}
	
	@Test
	public void testGetDetailVille() {
		String nomCommune = "dunkerque";
		Ville ville = null;
		Mockito.when(villeRepository.findBySlug(nomCommune)).thenReturn(ville);
		
	}

}
