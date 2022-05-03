package com.gillianocampos.cursospringangular.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.gillianocampos.cursospringangular.entities.PagamentoComBoleto;

//classe que calcula a data de vencimento 1 semana depois do instante do pedido
//recebe parametro PagamentoComBoleto e uma data e ira colocar nesse pagamento uma datadevencimento 1 semana
@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto,Date instanteDoPedido) {
		//pega uma instancia de calendario
		Calendar cal = Calendar.getInstance();
		//define a data do calendar
		cal.setTime(instanteDoPedido);
		//acrescenta 7 dias
		cal.add(Calendar.DAY_OF_MONTH,7);
		//seto a datadevencimento
		pagto.setDataVencimento(cal.getTime());
	}
	
	//numa situação real é melhor trocar essa implementação acima e chamar um webservice que gera o boleto pra você e oce pega a data do vencimento do boleto
}
