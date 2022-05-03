package com.gillianocampos.cursospringangular.resources;



import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gillianocampos.cursospringangular.dto.CategoriaDTO;
import com.gillianocampos.cursospringangular.entities.Categoria;
import com.gillianocampos.cursospringangular.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	//Buscar UMA CATEGORIA DIRETO NO BANCO H2
	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id){
		Categoria obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);		
	}
	
	//@Valid para validar este obj antes de passar para frente
	@RequestMapping(method = RequestMethod.POST) 
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDTO) {
		//para converte Dto fazer um metodo na classe de serviço metodo fromDTO 
		Categoria obj = service.fromDTO(objDTO);
		obj = service.inserir(obj); // o save do service retorna um obj
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	//@Valid para validar conforme na classe CategoriaDTO
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@ Valid @RequestBody CategoriaDTO objDTO, @PathVariable Integer id){
		Categoria obj = service.fromDTO(objDTO); // converte objDTO para obj
		obj.setId(id); //só para garantir que objeto esta com id da requisição
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value= "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete (@PathVariable Integer id){
		service.delete(id); //incluir  o metodo na classe de excecao ResourceExceptionHandler o tratamento
		return ResponseEntity.noContent().build();
	}
	
	//fazer DTO para o resultado mostrar so a lista de categorias sem os produtos dela
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll(){
		List<Categoria> lista = service.findAll(); //lista recebeu os objetos e abaixo converter para lista DTO
		List<CategoriaDTO> listaDTO = lista.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDTO);
	}
	
	
	//endpoint get para chamar paginação no servico o findPage
	//@RequestParam(value ="page", defaultValue = "0") onde value é o nome e defaultvalue é o valor padrao onde 0 é a primeira pagina 1 é a segunda.. para que sejam parametros sejam opcionais
	@RequestMapping(value = "/page" ,method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value ="page", defaultValue = "0") Integer page,
			@RequestParam(value ="linhaporPagina", defaultValue = "24")Integer linhaporPagina,
			@RequestParam(value ="ordena", defaultValue = "name")String ordena,//ordena por name
			@RequestParam(value ="direction", defaultValue = "ASC")String direction){ //ordena ascendente
		Page<Categoria> lista = service.findPage(page, linhaporPagina, ordena, direction); 
		Page<CategoriaDTO> listaDTO = lista.map(obj -> new CategoriaDTO(obj)); //converte Page de categoria para Page DTo nao precisa do stream nem collection
		return ResponseEntity.ok().body(listaDTO); //conferir chamando localhost8080/categorias/page
		//testar 3 por pagina localhost:8080/categorias/page?linhaporPagina=3
		//localhost:8080/categorias/page?linhaporPagina=3&page=1 segunda pagina
	}
}
