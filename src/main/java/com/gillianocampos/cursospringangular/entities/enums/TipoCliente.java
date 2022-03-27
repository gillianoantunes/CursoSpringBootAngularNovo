package com.gillianocampos.cursospringangular.entities.enums;

public enum TipoCliente {
	
	PESSOAFISICA (1, "Pessoa Física"),
	PESSOAJURIDICA (2, "Pessoa Jurídica");
	
	//para armazenar o codigo acima e descrição
	private int cod;
	private String descricao;

	//construtor de tipo enumerado é private
	private TipoCliente(int cod, String descricao) {
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

	//metodo que recebe um codigo e ja retorna um tipo cliente ja instanciado 
	//static pois vai chamar esse metodo mesmo sem instancia objeto
	public static TipoCliente converteEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		//for que percorre todos valores possiveis do meu tipo enumerado 
		//se o cod for igual cod que chegou
		//esse metodo se chegar codigo 1 retorna pessoa fisica e 2 pessoa juridica
		for(TipoCliente x :TipoCliente.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		//se esgotar o for e nao achar o codigo, ele é invalido
		throw new IllegalArgumentException("Id inválido " + cod);
	}
	
	
}
