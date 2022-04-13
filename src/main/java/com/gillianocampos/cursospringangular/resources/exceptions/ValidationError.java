package com.gillianocampos.cursospringangular.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

// superclasse ErroPadrao
public class ValidationError extends ErroPadrao {
	
	private static final long serialVersionUID = 1L;

	//lista para gerar erros e criar get e set
	private List<FieldMessage> listaerrors = new ArrayList<>();
	
	//gerou autmatico
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}

	public List<FieldMessage> getErrors() {
		return listaerrors;
	}

	//mudar para acrescentar um erro de cada vez na lista
	public void addError(String nomeCampo, String message) {
		listaerrors.add(new FieldMessage(nomeCampo, message));
	}

}
