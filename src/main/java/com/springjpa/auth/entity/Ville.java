package com.springjpa.auth.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name ="villes")
public class Ville {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	private String departement;
	private String slug;
	private String nom;
	private String nomSimple;
	private String nomReel;
	private String nomSoundex;
	private String nomMetaphone;
	private String codePostal;
	private String commune;
	private String codeCommune;
	private Long arrondissement;
	private String canton;
	private Long amdi;
	private Long population_2010;
	private Long population_1999;
	private Long population_2012;
	private Long densite_2010;
	private Double surface;
	private Double longitude_deg;
	private Double latitude_deg;
	private String longitude_grd;
	private String latitude_grd;
	private String longitude_dms;
	private String latitude_dms;
	private Long zmin;
	private Long zmax;
	
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
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getNomSimple() {
		return nomSimple;
	}
	public void setNomSimple(String nomSimple) {
		this.nomSimple = nomSimple;
	}
	public String getNomReel() {
		return nomReel;
	}
	public void setNomReel(String nomReel) {
		this.nomReel = nomReel;
	}
	public String getNomSoundex() {
		return nomSoundex;
	}
	public void setNomSoundex(String nomSoundex) {
		this.nomSoundex = nomSoundex;
	}
	public String getNomMetaphone() {
		return nomMetaphone;
	}
	public void setNomMetaphone(String nomMetaphone) {
		this.nomMetaphone = nomMetaphone;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getCommune() {
		return commune;
	}
	public void setCommune(String commune) {
		this.commune = commune;
	}
	public String getCodeCommune() {
		return codeCommune;
	}
	public void setCodeCommune(String codeCommune) {
		this.codeCommune = codeCommune;
	}
	public Long getArrondissement() {
		return arrondissement;
	}
	public void setArrondissement(Long arrondissement) {
		this.arrondissement = arrondissement;
	}
	public String getCanton() {
		return canton;
	}
	public void setCanton(String canton) {
		this.canton = canton;
	}
	public Long getAmdi() {
		return amdi;
	}
	public void setAmdi(Long amdi) {
		this.amdi = amdi;
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
	public Long getDensite_2010() {
		return densite_2010;
	}
	public void setDensite_2010(Long densite_2010) {
		this.densite_2010 = densite_2010;
	}
	public Double getSurface() {
		return surface;
	}
	public void setSurface(Double surface) {
		this.surface = surface;
	}
	public Double getLongitude_deg() {
		return longitude_deg;
	}
	public void setLongitude_deg(Double longitude_deg) {
		this.longitude_deg = longitude_deg;
	}
	public Double getLatitude_deg() {
		return latitude_deg;
	}
	public void setLatitude_deg(Double latitude_deg) {
		this.latitude_deg = latitude_deg;
	}
	public String getLongitude_grd() {
		return longitude_grd;
	}
	public void setLongitude_grd(String longitude_grd) {
		this.longitude_grd = longitude_grd;
	}
	public String getLatitude_grd() {
		return latitude_grd;
	}
	public void setLatitude_grd(String latitude_grd) {
		this.latitude_grd = latitude_grd;
	}
	public String getLongitude_dms() {
		return longitude_dms;
	}
	public void setLongitude_dms(String longitude_dms) {
		this.longitude_dms = longitude_dms;
	}
	public String getLatitude_dms() {
		return latitude_dms;
	}
	public void setLatitude_dms(String latitude_dms) {
		this.latitude_dms = latitude_dms;
	}
	public Long getZmin() {
		return zmin;
	}
	public void setZmin(Long zmin) {
		this.zmin = zmin;
	}
	public Long getZmax() {
		return zmax;
	}
	public void setZmax(Long zmax) {
		this.zmax = zmax;
	}
	
	
	
	
	

}
