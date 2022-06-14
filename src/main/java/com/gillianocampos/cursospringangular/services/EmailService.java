package com.gillianocampos.cursospringangular.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.gillianocampos.cursospringangular.entities.Pedido;

///operações que servço de email irá oferecer
public interface EmailService {
	
	//interface com 2 operações email formato texto
	//operação confirmação de pedido
	void sendOrderConfirmationEmail(Pedido obj);
	
	//operação para enviar email formato texto
	void sendEmail(SimpleMailMessage msg);

	//Email HTML 2 operaçoes
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	
	//para enviar email no formato Html
	void sendHtmlEmail(MimeMessage msg);
	
	//agora vai em AbtractMailService criar os metodos para retornar o Html com os dados do pedido preenchidos 
	//com o Thymeleaf que esta na pasta resource/email
	//obs: tive que mudar nomes mo time life que referenciavacliente.nome para cliente.name que estava assim a classe cliente
	//mudei tbm no thymeleaf ValorTotal e SubTotal que estavam minuscula nas classes
}

