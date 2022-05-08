package com.gillianocampos.cursospringangular.config;

//clonei a classe TestConfig
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gillianocampos.cursospringangular.services.DbService;

// administra qual profile application.properties vou usar
//configura coisas especificas para cada profile que tiver no caso test
@Configuration
@Profile("dev") //mudei para dev qque é o apllication.properties e acrescentar no pom.xml a dependencia do mysql
public class DevConfig {
	
	@Autowired
	private DbService dbService;
	
	//para pegar o valor de spring.jpa.hibernate.ddl-auto no arquivo application-dev.properties
	//se for create é para criar o banco se for none não iria criar o banco novamente sempre que rodar o programa
	//joguei esse valor para a variavel strategy
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	
	//todos os beans serao ativados quando tiver ativo o profile test

	@Bean 
	public boolean instantiateDatabase90() throws ParseException{
		
		//se variavel strategy não for igual a create eu não faço nada
		if(!"create".equals(strategy)) {
			return false;
		}
	
		// se for igual a create chama o metodo na classe dbService para criar o banco de dados
		dbService.instantiateTestDatabase();
		return true;
	}
}
