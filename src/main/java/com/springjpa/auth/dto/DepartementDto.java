package com.springjpa.auth.dto;

public class DepartementDto {
	
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
	
	public DepartementDto(Long id, String code, String nom, String nomUppercase, String slug, String nomSoundex) {
		super();
		this.id = id;
		this.code = code;
		this.nom = nom;
		this.nomUppercase = nomUppercase;
		this.slug = slug;
		this.nomSoundex = nomSoundex;
	}
	
	public DepartementDto(String code, String nomUppercase) {
		super();
		this.code = code;
		this.nomUppercase = nomUppercase;
	}
	public DepartementDto() {
		super();
	}
	
	

}
