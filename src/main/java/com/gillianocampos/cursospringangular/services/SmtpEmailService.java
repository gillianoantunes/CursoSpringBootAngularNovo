package com.gillianocampos.cursospringangular.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

//classe que pega os dados de email que informei em application.test
public class SmtpEmailService extends AbstractEmailService {

	//injetar uma instancia do mailsender que é uma classe que automaticamente ele instancia com todos os dados de email que esta em application.dev
	@Autowired
	private MailSender mailSender;
	
	//copiei de MockMailService para mostrar o email no logger do springboot
	private static final Logger Log = LoggerFactory.getLogger(SmtpEmailService.class);


	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		//copiei os logs de MockMailService só que ao ines de dar log na minha msg eu vou enviar o email
		Log.info("Enviando de email...");
		//mudei aqui Log.info(msg.toString());
		//para enviar chamo o objeto mailSender.send(msg) com a msg
		mailSender.send(msg);
		Log.info("Email enviado");
		//para testar mudar perfil para test em application. e ir na classe de config TestConfig e mudar o bean para retornar new SmtpEmailService
	}

}
