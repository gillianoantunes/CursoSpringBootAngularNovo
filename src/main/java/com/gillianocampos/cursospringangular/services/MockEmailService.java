package com.gillianocampos.cursospringangular.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {

	// para mostrar o email no logger do spingboot
	private static final Logger Log = LoggerFactory.getLogger(MockEmailService.class);

	@Override
	// para imprimir o logger
	public void sendEmail(SimpleMailMessage msg) {
		Log.info("Simulando envio de email...");
		Log.info(msg.toString());
		Log.info("Email enviado");
	}

}
