package com.gillianocampos.cursospringangular.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

//classe que pega os dados de email que informei em application.test
public class SmtpEmailService extends AbstractEmailService {

	//injetar uma instancia do mailsender que é uma classe que automaticamente ele instancia com todos os dados de email que esta em application.test
	@Autowired
	private MailSender mailSender;
	
	///para enviar email formato html MimeMessage
	@Autowired
	private JavaMailSender javaMailSender;
	
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



	//igual fizemos no metodo sendEmail acima para enviar Email no formato Texto
	@Override
	public void sendHtmlEmail(MimeMessage msg) {
				Log.info("Enviando de email HTML...");
				//para enviar Mimeessage html usar JavaMailSender tem que instanciar acima
				javaMailSender.send(msg);
				Log.info("Email enviado");
		//para terminar ir em PedidoService e mudar a chamada para o metodo sendOrderConfirmationHtmlEmail
	}

}
