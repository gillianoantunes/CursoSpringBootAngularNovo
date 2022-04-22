package com.gillianocampos.cursospringangular.services.validation;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.gillianocampos.cursospringangular.dto.ClienteDTO;
import com.gillianocampos.cursospringangular.entities.Cliente;
import com.gillianocampos.cursospringangular.repositories.ClienteRepository;
import com.gillianocampos.cursospringangular.resources.exceptions.FieldMessage;

//mudar o nome ClientUpdateValidator e entre<nomedaanotaçãoquecolocaremos e a classe que ira aceitar esta anotação que sera ClientDTO que usamos para alterar>
public class ClienteUpdateValidator implements ConstraintValidator<ClientUpdate, ClienteDTO> {
	
	//injetar ClienteRepository
	@Autowired
	private ClienteRepository repo;
	
	//para buscar id do objeto e comparar com outro pra ver se email é de outro cliente
	@Autowired
	private HttpServletRequest request;
	
	@Override
	//trocar nome para ClienteUpdate aqui
	public void initialize( ClientUpdate ann) {
	}

	
	//trocar tipo aqui para ClienteDTO>
	@Override
	//retorna true ou falso se der erro
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		// requisição para pegar o id do objeto que estou alterando
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();

		
		
		//no put testa se email ja existe desde que nao seja o cliente que estamos alterando
		//o id vem da URI quando altera pra isso temos que instanciar um httpServeletRequeste la em cima
		Cliente aux = repo.findByEmail(objDto.getEmail());
		//se aux diferente de nulo e id do objeto não é igual ao id do objeto que veio na url
		if(aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getNomeCampo()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}

