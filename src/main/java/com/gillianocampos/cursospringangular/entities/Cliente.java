package com.gillianocampos.cursospringangular.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gillianocampos.cursospringangular.entities.enums.TipoCliente;

@Entity
public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private String cpfOuCnpj;
	
	//enum mas mudei para Integer private TipoCliente tipo;
	//o tipo é armazenado como inteiro mas para o mundo externo a classe expoe a descrição
	private Integer tipo;
	
	
	@OneToMany (mappedBy = "cliente")
	private List<Endereco> enderecos = new ArrayList<>();
	
	//como a classe telefone só tem um atributo eu não vou criar a classe
	//farei um set que é uma lista ou melhor conjunto que não aceita repetições
	//como telefone nao fiz tabela ela é uma entidade fraca usar @ElementColection para mapear e criar no banco este telefone 
	//para colocar nome @CollectionTable criando no banco a tabela telefone com id do cliente
	@ElementCollection
	@CollectionTable(name = "Telefone")
	private Set<String> telefones = new HashSet<>();
	
	@JsonIgnore //cliente com pedido os pedidos de um clientes nao serao serializados
	//relacionamento com pedido 1 cliente tem varios pedidos
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos = new ArrayList<>();
	
	public Cliente() {
		
	}

	public Cliente(Integer id, String name, String email, String cpfOuCnpj, TipoCliente tipo) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		//ternario se tipo ==null atribui null senao atribui get.cod
		this.tipo =(tipo==null)? null : tipo.getCod(); //mudar aqui para getcod integer 
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

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	
	public TipoCliente getTipo() {
		return TipoCliente.converteEnum(tipo); //para retorna um tipo cliente tenho que chamar o metodo la no enum que conver o codigo para cliente com codigo e descrição
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod(); //armazena somente o codigo Integer
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	
	
}
