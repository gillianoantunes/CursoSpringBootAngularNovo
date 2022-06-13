package com.gillianocampos.cursospringangular.services;

import org.springframework.mail.SimpleMailMessage;

import com.gillianocampos.cursospringangular.entities.Pedido;

///operações que servço de email irá oferecer
public interface EmailService {
	
	//interface com 2 operações
	//operação confirmação de pedido
	void sendOrderConfirmationEmail(Pedido obj);
	
	//operação para enviar email
	void sendEmail(SimpleMailMessage msg);

}
