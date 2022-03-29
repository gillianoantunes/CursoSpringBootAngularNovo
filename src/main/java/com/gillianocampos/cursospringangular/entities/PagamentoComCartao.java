package com.gillianocampos.cursospringangular.entities;

import javax.persistence.Entity;

import com.gillianocampos.cursospringangular.entities.enums.EstadoPagamento;

//subclasse de Pagamento e não faz hashcode e equals pois a comparação ja esta na superclasse pagamento
//tambem não precisa por implements serializable na subclasse mas tem que colocar o numero de versao private static final long serialVersionUID = 1L;
@Entity
public class PagamentoComCartao extends Pagamento{

	private static final long serialVersionUID = 1L;
	
	private Integer numeroDeParcelas;
	
	public PagamentoComCartao() {
		
	}
	//o construtor é criado source -> generator constructor from superclasse
	//acrescentar o numeroDeParcelas que é o atributo desta subclasse
	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
		//acrescentei
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	//get e set
	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}
	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	
}
