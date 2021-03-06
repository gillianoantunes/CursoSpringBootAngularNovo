package com.gillianocampos.cursospringangular.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.gillianocampos.cursospringangular.services.validation.ClientInsert;

//classe para cadastrar um novo cliente com endereço,telefones,cidades e estado
@ClientInsert //anotação que vou criar para validar cpf ou cnpj dependendo do tipo do cliente.criar classe no serviço no pacote validation chamada ClienteInsert
public class ClienteNewDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//peguei todos dados da classe cliente endereço menos o Id e criei telefones
	@NotEmpty(message = "Preechimento obrigatório")
	@Length(min =5, max = 120 , message="O tamanho deve ser entre 5 e 120 caracteres")
	private String name;
	
	@NotEmpty(message = "Preechimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	
	//verificar para validadr qual tipo do cliente e assim validar cpf ou cnpj
	//se fosse so cpf ou cnpf tem anotação @CPF e @CNPJ entao usar la na classe @ClienteInsert que sera customizada
	@NotEmpty(message = "Preechimento obrigatório")
	private String cpfOuCnpj;
	
	private Integer tipo;
	
	@NotEmpty(message = "Preechimento obrigatório")
	private String logradouro;

	@NotEmpty(message = "Preechimento obrigatório")
	private String numero;
	
	private String complemento;
	
	private String bairro;
	
	@NotEmpty(message = "Preechimento obrigatório")
	private String cep;
	
	@NotEmpty(message = "Preechimento obrigatório")
	private String telefone1;
	
	private String telefone2;
	private String telefone3;
	
	//para cadastrar endereço vou ter que escolher a cidade entao pegar codigo de uma cidade
	private Integer cidadeId;
	
	public ClienteNewDTO() {
		
	}

	//get e set
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

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}
	
	
}
