package com.gillianocampos.cursospringangular.services.exceptions;



public class ExcecaoObjetoNaoEncontrado extends RuntimeException  {

	private static final long serialVersionUID = 1L;
	
	public ExcecaoObjetoNaoEncontrado(String msg) {
		super(msg);
	}
	
	public ExcecaoObjetoNaoEncontrado(String msg, Throwable causa) {
		super(msg,causa);
	}
}
