package com.gillianocampos.cursospringangular.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.gillianocampos.cursospringangular.entities.enums.EstadoPagamento;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) //na superclasse
//abstract para proteger e nao conseguir instanciar a classe pagamento apenas com new da subclasse
//exemplo Pagamento pag1 = new PagamentocomBoleto();
public abstract class Pagamento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	//não vou colocar o id de pagamento automatico pois esse pagamento tera o id do pedido correspondente vou colocar no atributo do pedido logo abaixo
	//colocar no atributo pedido 	@MapsId //para garantir que este id seja o mesmo do pedido
	private Integer id;
	//enum mas mudei para Integer private EstadoPagamento estado;
	//o esatdo é armazenado como inteiro mas para o mundo externo a classe expoe a descrição
	private Integer estado;
	
	//1 pagamento tem é de 1 pedido
	@OneToOne
	@JoinColumn(name = "pedido_id") //coloquei o nome dessa coluna como pedido_ididentificar no banco quem é o campo que está mapeando com pedido
	@MapsId //para garantir que este id seja o mesmo do pedido
	private Pedido pedido;
	
	//construtores
	public Pagamento() {
		
	}

	public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
		super();
		this.id = id;
		this.estado = estado.getCod();
		this.pedido = pedido;
	}

	
	//get e set
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EstadoPagamento getEstado() {
		return EstadoPagamento.converteEnum(estado);
	}

	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getCod();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	
	//hascode e equals
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
		Pagamento other = (Pagamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	
	
}
