package com.gillianocampos.cursospringangular.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gillianocampos.cursospringangular.entities.PagamentoComBoleto;
import com.gillianocampos.cursospringangular.entities.PagamentoComCartao;

//classe para avisar ao JasonType que as subclasse pagamentocomboleto e pagamentocomcartao serao registradas 
//codigo padrao pronto
@Configuration
public class JacksonConfig {
// https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-without-hinting-the-pare
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				//so mudar o nome indicando as subclasses no caso pagamentocomboleto e pagamentocomcartao
				objectMapper.registerSubtypes(PagamentoComCartao.class);
				objectMapper.registerSubtypes(PagamentoComBoleto.class);
				super.configure(objectMapper);
			}
		};
		return builder;
	}
}