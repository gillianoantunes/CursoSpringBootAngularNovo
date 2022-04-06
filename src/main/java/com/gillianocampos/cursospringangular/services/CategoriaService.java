package com.gillianocampos.cursospringangular.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gillianocampos.cursospringangular.entities.Categoria;
import com.gillianocampos.cursospringangular.repositories.CategoriaRepository;
import com.gillianocampos.cursospringangular.services.exceptions.ExcecaoObjetoNaoEncontrado;


@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		//se o objeto nção existir lança a exceção
		return obj.orElseThrow(() -> new ExcecaoObjetoNaoEncontrado("Objeto não encontrado! Id " + id + 
												", Tipo: " + Categoria.class.getName()));
		}
	
	public Categoria inserir(Categoria obj) {
		obj.setId(null); //objeto que esta chegando tem que ter id nulo
		return repo.save(obj); //save retorna om obj
	}
	
	public Categoria update(Categoria obj) {
		buscar(obj.getId());//verifica se o id existe chamando o metodo buscar acima
		return repo.save(obj); //quando id vale null o save insere, quando não é nulo ele atualiza o objeto
	}
}
