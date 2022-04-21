package com.gillianocampos.cursospringangular.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.gillianocampos.cursospringangular.entities.Cliente;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Preechimento obrigatório")
	@Length(min =5, max = 120 , message="O tamanho deve ser entre 5 e 120 caracteres")
	private String name;
	
	@NotEmpty(message = "Preechimento obrigatório")
	@Email (message = "Email inválido") //verifica se tem @ e se trm dominio
	private String email;

	public ClienteDTO(){
		
	}
	
	public ClienteDTO(Cliente obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.email = obj.getEmail();
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getcpfOuCnpj() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
