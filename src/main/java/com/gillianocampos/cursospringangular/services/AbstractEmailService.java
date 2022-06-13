package com.gillianocampos.cursospringangular.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.gillianocampos.cursospringangular.entities.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}") // pega o que esta em appliation,properties em default.sender e joga o email para variavel sender
	private String  sender;
	
	//implemenatr o metodo de confirmaçao do pedido que esta na interface EmailService
	@Override //sobescreve o metodo
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		///chama o metodo sendEmail da interface EmailService
		sendEmail(sm);
	}

	//protected pode ser acessado por subclasses
	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		//destinatario de email pega o email que esta cadastrado no cliente
		sm.setTo(obj.getCliente().getEmail());
		//remetente de email pega o que esta no application.properties em default.sender
		sm.setFrom(sender); 
		//assunto
		sm.setSubject("Pedido confirmado! Código: " + obj.getId());
		//Data do email pegando do servidor
		sm.setSentDate(new Date(System.currentTimeMillis()));
		//corpo do email retorna o metodo toString
		sm.setText(obj.toString());
		return sm;
	}

}
