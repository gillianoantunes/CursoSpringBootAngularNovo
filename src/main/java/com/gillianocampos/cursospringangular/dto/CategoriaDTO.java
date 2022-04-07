package com.gillianocampos.cursospringangular.dto;

import java.io.Serializable;

import com.gillianocampos.cursospringangular.entities.Categoria;

public class CategoriaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	
	public CategoriaDTO() {
		
	}
	
	//passa os dados de Categoria para Categoria DTO
	public CategoriaDTO(Categoria obj) {
		this.id = obj.getId();
		this.name = obj.getName();
	}
	
	//get e set
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
