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
}