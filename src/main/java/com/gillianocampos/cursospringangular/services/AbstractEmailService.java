package com.gillianocampos.cursospringangular.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.gillianocampos.cursospringangular.entities.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}") // pega o que esta em appliation,properties em default.sender e joga o email para variavel sender
	private String  sender;
	
	
	//usado para retorna o html do template na forma de String quando usamos o email html
	@Autowired
	private TemplateEngine templateEngine;
	

	@Autowired
	private JavaMailSender javaMailSender;
	
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

	//pega o tamplate Html que criamos joga os dados do pedido no tamplate e retrona uma string com esse html preenchido
	protected String htmlFromTemplatePedido(Pedido obj) {
		//importar context do thymeleaf 
		//context envai o objeto Pedido para o template
		Context context =  new Context();
		//enviando objeto pedido para o tamplate 
		//primeiro parametro é o apelido que esta no tamplate la no codigo html usamos pedido.id pedido.instante,etc
		//segundo paramentro é o objeto que sera passado
		context.setVariable("pedido", obj);
		//retorna o html em formato de string para isso usamos o templateEngine, temos que instanciar la em cima
		//paramentro(caminhodotemplaten a partir da pasta resource/templates,o objeto context
		//retorna o template ja retorna o html em string
		return templateEngine.process("email/confirmacaoPedido", context);
	}
	
	//criamos o metodo acima para o mockemailService, agora sera para html
	
	//vamos usar agora o mimeMessage que criamos na classe EmailService
	//criar o metodo prepareMimeMessageFromPedido para preparar o mimemessage a partir do pedido
	// e enviamos no outro metodo que criamos no EmailService que é sendHtmlEmail
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido obj) {
		try {
			MimeMessage mm = prepareMimeMessageFromPedido(obj); //tratar exceção bloco try
			//chama o metodo sendEmail da interface EmailService
			sendHtmlEmail(mm);
		}
		catch(MessagingException e) {
			//se der erro no envio de  emailhtml eu chamo envio de metodo texto plano
			sendOrderConfirmationEmail(obj);
		}
	}
	
	//protected para ser reimplementado numa possivel subclasse
	protected  MimeMessage prepareMimeMessageFromPedido(Pedido obj) throws MessagingException {
		// injetar acima o JavaMailSender para instanciar o MimeMessage
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		//para atribuir valores a essa message instanciar o MimeMessageHelper
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true); //tratar essa exceçao clicae add Trows
		
		//atribuir valores para o email
		//setTo para quem vi o email
		mmh.setTo(obj.getCliente().getEmail());
		//remetente pega o sender do application.properties que injetei acima
		mmh.setFrom(sender);	
		//assunto do email setSubject
		mmh.setSubject("Pedido confirmado! Código: " + obj.getId());
		//instante do Email
		mmh.setSentDate(new Date (System.currentTimeMillis()));
		//corpo do email sera o email html processado pelo Thymeleaf chamar o metodo HtmlFromTemplate
		//segundo parametro true para indicar que esse conteudo é um html
		mmh.setText(htmlFromTemplatePedido(obj), true);
		
		//retoanr o MimeMessage
		return mimeMessage;
		
		//agora em mockEmailService implementar os novos contratos de EmailService que criamos para html
	}
}
