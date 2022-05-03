package com.gillianocampos.cursospringangular.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;

	// chave é do tipo da classe auxiliar ItemPedidoPK onde tem 2 chaves primarias
	// de Produto e Pedido

	@JsonIgnore
	@EmbeddedId //id que esta embutido em uma classe auxiliar ItemPedidoPK
	private ItemPedidoPK id = new ItemPedidoPK();

	private Double desconto;
	private Integer quantidade;
	private Double preco;

	public ItemPedido() {

	}

	// troquei argumento ItemPedidoPK por pedido e produto e passei id o pedido e o
	// produto
	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
		super();
		// id recebe o pedido e o produto que chegou no argumento
		id.setPedido(pedido);
		id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	//metodo para calcular subtotal dos itens
	//qaundo eu ponho o get na frente do nome do metodo getSubTotal o Json ja conhece e aparece esse subtotal quando dou um get aparece o campo subtotal na pesquisa
	public double getSubTotal() {
		return (preco - desconto) * quantidade;
	}
	
	
	
	@JsonIgnore //referencia ciclica tudo que começa com get ele serializa tem que ignorar
	// get de Produto e Pedido para ter acesso direto ao Pedido e Produto fora da
	// minha classe ItemPedido para recuper um pedido e produto
	public Pedido getPedido() {
		return id.getPedido();
	}

	// set de Pedido e Produto para itempedido possa instanciar um novoitempedido e associar um pedido e um produto a este itemdepedido
	///sem o set so consigo instanciar por meio do construtor  com argumentos acima, mas o framework ele nao utiliza o construtor e sim o metodo set
	public void setPedido(Pedido pedido) {
		id.setPedido(pedido);
	}
	
	
	//nao usei @jsonIgnore para aparecer o produto nos items
	public Produto getProduto() {
		return id.getProduto();
	}
	
	//setProduto
	public void setProduto(Produto produto) {
		id.setProduto(produto);
	}

	
	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
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
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
