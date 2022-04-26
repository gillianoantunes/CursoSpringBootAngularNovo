package com.gillianocampos.cursospringangular.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gillianocampos.cursospringangular.entities.Categoria;
import com.gillianocampos.cursospringangular.entities.Produto;
import com.gillianocampos.cursospringangular.repositories.CategoriaRepository;
import com.gillianocampos.cursospringangular.repositories.ProdutoRepository;
import com.gillianocampos.cursospringangular.services.exceptions.ExcecaoObjetoNaoEncontrado;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	//para buscar as categorias no metodo search List<Categoria> categorias = categoriaRepository.findAllById(ids);
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Produto buscar(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		// se o objeto nção existir lança a exceção
		return obj.orElseThrow(() -> new ExcecaoObjetoNaoEncontrado(
				"Objeto não encontrado! Id " + id + ", Tipo: " + Produto.class.getName()));
	}

	// operação chamada search pesquisa de page que vai receber uma sring de nome
	// que ira ser pesquisado copiar este metodo do CategoriaService
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linhaporPagina, String ordena,
			String direction) {
		PageRequest pageRequest = PageRequest.of(page, linhaporPagina, Direction.valueOf(direction), ordena);
		//criar metodo search no repository passando um nome, uma lista de categorias e argumento da pagina
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
}
