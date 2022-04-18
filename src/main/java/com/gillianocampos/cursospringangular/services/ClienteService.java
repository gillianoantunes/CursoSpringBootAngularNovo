package com.gillianocampos.cursospringangular.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gillianocampos.cursospringangular.dto.ClienteDTO;
import com.gillianocampos.cursospringangular.dto.ClienteNewDTO;
import com.gillianocampos.cursospringangular.entities.Cidade;
import com.gillianocampos.cursospringangular.entities.Cliente;
import com.gillianocampos.cursospringangular.entities.Endereco;
import com.gillianocampos.cursospringangular.entities.enums.TipoCliente;
import com.gillianocampos.cursospringangular.repositories.ClienteRepository;
import com.gillianocampos.cursospringangular.repositories.EnderecoRepository;
import com.gillianocampos.cursospringangular.services.exceptions.ExcecaoIntegridade;
import com.gillianocampos.cursospringangular.services.exceptions.ExcecaoObjetoNaoEncontrado;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		// se o objeto nção existir lança a exceção
		return obj.orElseThrow(() -> new ExcecaoObjetoNaoEncontrado(
				"Objeto não encontrado! Id " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	@Transactional //para garantir que salva tudo clientes, endereço telefones ou nao salva nada
	public Cliente inserir(Cliente obj) {
		obj.setId(null); // objeto que esta chegando tem que ter id nulo
		//tem que salvar o endereço do cliente 
		obj = repo.save(obj); //salvo o cliente sem endereço
		enderecoRepository.saveAll(obj.getEnderecos()); // e agora salvo os endereços no cliente
		return obj;
	}

	public Cliente update(Cliente obj) {
		// quando alterar buscar e instanciar um cliente a partir de um banco de dados
		// para cpf e tipo nao fica null
		Cliente newObj = buscar(obj.getId());
		// atualizar os dados com metodo updateData
		updateData(newObj, obj);
		// salvar o newObj
		return repo.save(newObj);
	}

	// metodo atualiza o newobj alterado e agora ele alterar os daods nome e email e
	// mantem o cpf e tipo que era do obj alterado
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());

	}

	public void delete(Integer id) {
		buscar(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {

			throw new ExcecaoIntegridade("Não é possível excluir porque há pedidos relacionados");

		}

	}

	public Page<Cliente> findPage(Integer page, Integer linhaporPagina, String ordena, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linhaporPagina, Direction.valueOf(direction), ordena);
		return repo.findAll(pageRequest); // chama o findAll passando o Page
	}

	// metodo converte a partir de um objDTO para obj
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getName(), objDTO.getEmail(), null, null);
	}

	// metodo converte a partir de um objDTO para obj
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		//converteEnum metodo que fizer na classe TipoCliente 
			Cliente cli = new Cliente(null, objDTO.getName(), objDTO.getEmail(), objDTO.getCpfOuCnpj(),TipoCliente.converteEnum(objDTO.getTipo()));
			Cidade cid = new Cidade(objDTO.getCidadeId(), null,null);
			Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(),objDTO.getBairro(),objDTO.getCep(), cli, cid);
			//incluir endereço na lista de endereços do cliente e telefone1 que é obrigatorio e os outros se tiver telefone2 e 3 que é opcional
			cli.getEnderecos().add(end);
			cli.getTelefones().add(objDTO.getTelefone1());
			//se telefone 2 ou 3 for diferente de nulo eu adiciono pois sao opcionais
			if(objDTO.getTelefone2() != null) {
				cli.getTelefones().add(objDTO.getTelefone2());
			}
			if(objDTO.getTelefone3() != null) {
				cli.getTelefones().add(objDTO.getTelefone3());
			}
			
			return cli;
		}

}
