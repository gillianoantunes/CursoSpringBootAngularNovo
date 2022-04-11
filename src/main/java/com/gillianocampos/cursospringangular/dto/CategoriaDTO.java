package com.gillianocampos.cursospringangular.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.gillianocampos.cursospringangular.entities.Categoria;

public class CategoriaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//validação do campo name
    //@NotEmpty name nao pode ser vazio se estiver dar uma mensagem
	//@Length pata tamanho maximo e minimo caso contrario da mensagem
	//para usar esss anotações colocar no pom xml
	//<dependency>
	//<groupId>org.springframework.boot</groupId>
	//<artifactId>spring-boot-starter-validation</artifactId>
    //</dependency>
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min=5 , max =80, message ="Tamanho deve ser entre 5 e 80 caracteres")
	//agora ir no Resource mudar para dto e usar as anotação
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
