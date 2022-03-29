package com.gillianocampos.cursospringangular.entities;

import java.util.Date;

import javax.persistence.Entity;

import com.gillianocampos.cursospringangular.entities.enums.EstadoPagamento;

//subclasse de Pagamento e não faz hashcode e equals pois a comparação ja esta na superclasse pagamento
//tambem não precisa por implements serializable na subclasse mas tem que colocar o numero de versao private static final long serialVersionUID = 1L;
@Entity
public class PagamentoComBoleto extends Pagamento{
	
	private static final long serialVersionUID = 1L;
	
	private Date dataVencimento;
	private Date dataPagamento;
	

	//construtores
	public PagamentoComBoleto() {
		
	}

	//o construtor é criado source -> generator constructor from superclasse
	////acrescentei com atributos desta subclasse dataVencimento e dataPagamento
	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		//acrescentei 
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	
	//get e set
	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	

	
}
