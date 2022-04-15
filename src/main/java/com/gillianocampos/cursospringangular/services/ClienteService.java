package com.gillianocampos.cursospringangular.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gillianocampos.cursospringangular.dto.ClienteDTO;
import com.gillianocampos.cursospringangular.entities.Cliente;
import com.gillianocampos.cursospringangular.repositories.ClienteRepository;
import com.gillianocampos.cursospringangular.services.exceptions.ExcecaoIntegridade;
import com.gillianocampos.cursospringangular.services.exceptions.ExcecaoObjetoNaoEncontrado;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		// se o objeto nção existir lança a exceção
		return obj.orElseThrow(() -> new ExcecaoObjetoNaoEncontrado(
				"Objeto não encontrado! Id " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Cliente inserir(Cliente obj) {
		obj.setId(null); // objeto que esta chegando tem que ter id nulo
		return repo.save(obj);
	}

	public Cliente update(Cliente obj) {
		//quando alterar buscar e instanciar um cliente a partir de um banco de dados para cpf e tipo nao fica null
		Cliente newObj = buscar(obj.getId());
		//atualizar os dados com metodo updateData
		updateData(newObj, obj);
		//salvar o newObj
		return repo.save(newObj); 
	}

	//metodo atualiza o newobj alterado e agora ele alterar os daods nome e email e mantem o cpf e tipo que era do obj alterado
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		
	}

	public void delete(Integer id) {
		buscar(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {

			throw new ExcecaoIntegridade("Não é possível excluir porque há entidades relacionadas");

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

}
