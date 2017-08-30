package com.springjpa.auth.dto;

public class VilleDto {
	
	private Long id;
	private String departement;
	private String nom;
	private String codePostal;
	private Long population_2010;
	private Long population_1999;
	private Long population_2012;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDepartement() {
		return departement;
	}
	public void setDepartement(String departement) {
		this.departement = departement;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public Long getPopulation_2010() {
		return population_2010;
	}
	public void setPopulation_2010(Long population_2010) {
		this.population_2010 = population_2010;
	}
	public Long getPopulation_1999() {
		return population_1999;
	}
	public void setPopulation_1999(Long population_1999) {
		this.population_1999 = population_1999;
	}
	public Long getPopulation_2012() {
		return population_2012;
	}
	public void setPopulation_2012(Long population_2012) {
		this.population_2012 = population_2012;
	}

	
	public VilleDto(Long id, String nom, String codePostal, Long population_1999, Long population_2010,
			Long population_2012, String departement) {
		super();
		
		this.id = id;
		this.nom = nom;
		this.codePostal = codePostal;
		this.population_1999 = population_1999;
		this.population_2010 = population_2010;
		this.population_2012 = population_2012;
		this.departement = departement;
	}
	
	public VilleDto() {
		super();
	}


}
