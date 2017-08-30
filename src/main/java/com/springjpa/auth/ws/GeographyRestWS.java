package com.springjpa.auth.ws;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.context.annotation.Description;

import com.springjpa.auth.dto.DepartementDto;
import com.springjpa.auth.dto.VilleDto;

@Description("Geography ressource : permet de gerer les methodes liees a la recherche de departements et villes")
public interface GeographyRestWS {
	
	@GET
	@Path("/departements")
	@Produces("application/json; charset=UTF-8")
	public List<DepartementDto> getListDepartements();
	
	@GET
	@Path("/departements/{id}")
	@Produces("application/json; charset=UTF-8")
	public DepartementDto getDepartement (@PathParam ("id") Long id);
	
	@GET
	@Path("/villes")
	@Produces("application/json; charset=UTF-8")
	public List<VilleDto> getListVilles(String departement);
	
	@GET
	@Path("/villes/{id}")
	@Produces("application/json; charset=UTF-8")
	public VilleDto getVille(@PathParam ("id") Long id);
	
	
	

}
