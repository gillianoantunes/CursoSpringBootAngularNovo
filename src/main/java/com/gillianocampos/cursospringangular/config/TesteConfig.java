package com.gillianocampos.cursospringangular.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gillianocampos.cursospringangular.services.DbService;

// administra qual profile application.properties vou usar
//configura coisas especificas para cada profile que tiver no caso test
@Configuration
@Profile("test")
public class TesteConfig {
	
	@Autowired
	private DbService dbService;
	
	
	//todos os beans serao ativados quando tiver ativo o profile test

	@Bean 
	public boolean instantiateDatabase90() throws ParseException{
		//chama o metodo na classe dbService 
		dbService.instantiateTestDatabase();
		return true;
	}
}
