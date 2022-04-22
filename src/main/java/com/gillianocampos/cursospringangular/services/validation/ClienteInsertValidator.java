package com.gillianocampos.cursospringangular.services.validation;


import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.gillianocampos.cursospringangular.dto.ClienteNewDTO;
import com.gillianocampos.cursospringangular.entities.Cliente;
import com.gillianocampos.cursospringangular.entities.enums.TipoCliente;
import com.gillianocampos.cursospringangular.repositories.ClienteRepository;
import com.gillianocampos.cursospringangular.resources.exceptions.FieldMessage;
import com.gillianocampos.cursospringangular.services.validation.utils.BR;

//mudar o nome ClientInsertValidator e entre<nomedaanotaçãoquecolocaremos e a classe que ira aceitar esta anotação que sera ClientNewDTO>
public class ClienteInsertValidator implements ConstraintValidator<ClientInsert, ClienteNewDTO> {
	
	//injetar ClienteRepository
	@Autowired
	private ClienteRepository repo;
	
	@Override
	//trocar nome para ClienteInsert aqui
	public void initialize( ClientInsert ann) {
	}

	
	//trocar tipo aqui para ClienteNewDTO>
	@Override
	//retorna true ou falso se der erro
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();

		// testa qual tipo de cliente para ver se é cpf ou cnpj , criar classe BR
		//se o tipo do meu dto for igual a pessoa fisica e se o cpf não for valido eu adiciono o erro
		//BR é uma classe que testa
		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF Inválido"));
		}
		
		//testa cnpj onde BR é uma classe que testa
		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido"));
		}
		
		//no post testando email aux recebe email que esta inserido e testa se esse aux é diferente de nulo significa que existe
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getNomeCampo()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}

