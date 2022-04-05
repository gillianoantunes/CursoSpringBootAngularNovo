package com.gillianocampos.cursospringangular.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Double preco;
	
	@JsonIgnore //para esconder a lista de produtos quando pesquisar por categorias id
  //@ManyToManyFAZ APENAS DE UM LADO NO CASO FIZEMOS NA CLASSE PRODUTO
	@ManyToMany
	@JoinTable(name = "PRODUTO_CATEGORIA",
	           joinColumns = @JoinColumn(name = "produto_id"),
	           inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private List<Categoria> categorias = new ArrayList<>();
	
	@JsonIgnore
	//produto tem lista de itens de pedido
	@OneToMany(mappedBy = "id.produto") //mapeado do outro lado pelo id.produto onde id usa classe auxiliar ItemPedidoPK que tem atributo produto
	private Set<ItemPedido> itens = new HashSet<>();
	
	public Produto() {
		
	}

	public Produto(Integer id, String nome, Double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

	@JsonIgnore //para tambem nao mostrar a lista de pedido a partir de produtos
	//metodo para montar lista de pedidos associado aos itens
	public List<Pedido> getPedidos(){
		List<Pedido> lista = new ArrayList<>(); //crio a lista
		for (ItemPedido x : itens) { //percorrer a lista de items que ja existe nesta classe
			lista.add(x.getPedido()); //adicionar os pedidos
		}
		return lista; //retornar lista
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	
	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}
	
	
	//hascode
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	


	
	
}
