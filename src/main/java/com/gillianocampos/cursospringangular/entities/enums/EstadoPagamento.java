package com.gillianocampos.cursospringangular.entities.enums;

public enum EstadoPagamento {

	PENDENTE(1,"Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	//igual do EstadoPagamento
	//para armazenar o codigo acima e descrição
		private int cod;
		private String descricao;

		//construtor de tipo enumerado é private
		private EstadoPagamento(int cod, String descricao) {
			this.cod = cod;
			this.descricao = descricao;
		}

		//enum so faz get pois enum nunca muda nao tem set
		public int getCod() {
			return cod;
		}

		public String getDescricao() {
			return descricao;
		}

		//metodo que recebe um codigo e ja retorna um EstadoPagamento ja instanciado 
		//static pois vai chamar esse metodo mesmo sem instancia objeto
		public static EstadoPagamento converteEnum(Integer cod) {
			if (cod == null) {
				return null;
			}
			
			//for que percorre todos valores possiveis do meu tipo enumerado 
			//se o cod for igual cod que chegou
			//esse metodo se chegar codigo 1 retorna Pendente e 2 Quiatdo e 3 cancelado
			for(EstadoPagamento x :EstadoPagamento.values()) {
				if(cod.equals(x.getCod())) {
					return x;
				}
			}
			//se esgotar o for e nao achar o codigo, ele é invalido
			throw new IllegalArgumentException("Id inválido " + cod);
		}
		
		
	}
