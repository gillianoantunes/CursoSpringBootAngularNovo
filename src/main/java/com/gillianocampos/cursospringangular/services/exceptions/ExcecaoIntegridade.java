package com.gillianocampos.cursospringangular.services.exceptions;

//exceção caso der erro de integridad referencial ao deletar
public class ExcecaoIntegridade extends RuntimeException  {

	private static final long serialVersionUID = 1L;
	
	public ExcecaoIntegridade(String msg) {
		super(msg);
	}
	
	public ExcecaoIntegridade(String msg, Throwable causa) {
		super(msg,causa);
	}
}
