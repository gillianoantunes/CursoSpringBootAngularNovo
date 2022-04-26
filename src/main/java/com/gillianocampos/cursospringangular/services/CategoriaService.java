package com.gillianocampos.cursospringangular.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gillianocampos.cursospringangular.dto.CategoriaDTO;
import com.gillianocampos.cursospringangular.entities.Categoria;
import com.gillianocampos.cursospringangular.repositories.CategoriaRepository;
import com.gillianocampos.cursospringangular.services.exceptions.ExcecaoIntegridade;
import com.gillianocampos.cursospringangular.services.exceptions.ExcecaoObjetoNaoEncontrado;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		// se o objeto nção existir lança a exceção
		return obj.orElseThrow(() -> new ExcecaoObjetoNaoEncontrado(
				"Objeto não encontrado! Id " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria inserir(Categoria obj) {
		obj.setId(null); // objeto que esta chegando tem que ter id nulo
		return repo.save(obj); // save retorna om obj
	}

	public Categoria update(Categoria obj) {
		//quando alterar buscar e instanciar uma categoria a partir de um banco de dados para cpf e tipo nao fica null
		Categoria newObj = buscar(obj.getId());
		//atualizar os dados com metodo updateData
		updateData(newObj, obj);
		//salvar o newObj
		return repo.save(newObj); 
	}
	
	//metodo atualiza o newobj alterado e agora ele alterar os daods nome
		private void updateData(Categoria newObj, Categoria obj) {
			newObj.setName(obj.getName());
		}

	// verificar a integridade referencial ex: apagar uma categoria que tem produtos
	// DataIntegrityViolationException criar exceçao personalizada
	public void delete(Integer id) {
		buscar(id); // verifica se o id existe chamando o metodo buscar acima
		try { // se der uma exceção eu capturo com catch
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			// cria uma exceção no pacote services.exceptions chamada ExcecaoIntegridade
			throw new ExcecaoIntegridade("Não é possível excluir uma categoria que possui produtos");
			// agora recebo essa excecao na camada de resource CategoriaResource
		}

	}

	
	public List<Categoria> findAll() {
		return repo.findAll();
    }
	
	//paginação chamando a busca de todas categorias pelo findAll passando o page
	public Page<Categoria> findPage(Integer page, Integer linhaporPagina, String ordena, String direction){
		PageRequest pageRequest = PageRequest.of(page,linhaporPagina, Direction.valueOf(direction),ordena);
		return repo.findAll(pageRequest); //chama o findAll passando o Page
		//agora fazer o endopoint no resource para chamar este metodo no post e no put para validação
     }
	
	//metodo converte a partir de um objDTO para obj
	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(), objDTO.getName());
	}
	
}