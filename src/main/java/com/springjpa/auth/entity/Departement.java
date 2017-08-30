package com.springjpa.auth.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "departement")
public class Departement {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	private String code;
	private String nom;
	private String nomUppercase;
	private String slug;
	private String nomSoundex;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getNomUppercase() {
		return nomUppercase;
	}
	public void setNomUppercase(String nomUppercase) {
		this.nomUppercase = nomUppercase;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public String getNomSoundex() {
		return nomSoundex;
	}
	public void setNomSoundex(String nomSoundex) {
		this.nomSoundex = nomSoundex;
	}	
	

}
