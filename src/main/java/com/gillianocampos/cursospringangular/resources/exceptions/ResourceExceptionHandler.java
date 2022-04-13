package com.gillianocampos.cursospringangular.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gillianocampos.cursospringangular.services.exceptions.ExcecaoIntegridade;
import com.gillianocampos.cursospringangular.services.exceptions.ExcecaoObjetoNaoEncontrado;

@ControllerAdvice
//para gerar erro informan tudo codigo do erro,status http 
//criei classe ErroPadrao para apoiar

public class ResourceExceptionHandler {

	// metodo padrao do @ControllerAdvice manipulador do erro com anotação
	// @ExceptionHandler
	@ExceptionHandler(ExcecaoObjetoNaoEncontrado.class)
	public ResponseEntity<ErroPadrao> objetoNaoEncontrado(ExcecaoObjetoNaoEncontrado e, HttpServletRequest request) {
		ErroPadrao erro = new ErroPadrao(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

	// integridade referencial
	@ExceptionHandler(ExcecaoIntegridade.class)
	public ResponseEntity<ErroPadrao> ExcecaodeIntegridade(ExcecaoIntegridade e, HttpServletRequest request) {
		ErroPadrao erro = new ErroPadrao(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	// erro de campo name vazio ou nao entre 5 e 80 carcteres
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErroPadrao> Validacao(MethodArgumentNotValidException e, HttpServletRequest request) {
	//mudar para criar classe ValidationError
		ValidationError erro = new ValidationError(HttpStatus.BAD_REQUEST.value(),"Erro de validação", System.currentTimeMillis());
		//percorre a lista de erro desta exceção. e.getBindingResult().getFieldErrors()) aceessa todos erros de campos
		for(FieldError x: e.getBindingResult().getFieldErrors()) {
			erro.addError(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
		//criar uma classe para gerar lista de erros com o nome do campo que deu erro chamada fieldMessage
	}
}
