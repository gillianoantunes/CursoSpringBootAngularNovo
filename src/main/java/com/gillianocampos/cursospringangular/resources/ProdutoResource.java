package com.gillianocampos.cursospringangular.resources;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gillianocampos.cursospringangular.dto.ProdutoDTO;
import com.gillianocampos.cursospringangular.entities.Produto;
import com.gillianocampos.cursospringangular.resources.utils.URL;
import com.gillianocampos.cursospringangular.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
	//endpoint busca por id
	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id){ 		
		Produto obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);		
	}
	
	//copiar CategoriaResource a busca paginada Page
	//endpoint get para chamar paginação no servico o findPage
		//@RequestParam(value ="page", defaultValue = "0") onde value é o nome e defaultvalue é o valor padrao onde 0 é a primeira pagina 1 é a segunda.. para que sejam parametros sejam opcionais
		@RequestMapping(method = RequestMethod.GET)
		public ResponseEntity<Page<ProdutoDTO>> findPage(
				//parametro de url nome e categorias incluso
				@RequestParam(value ="nome", defaultValue = "0")String nome,
				@RequestParam(value ="categorias", defaultValue = "0") String categorias,
				@RequestParam(value ="page", defaultValue = "0") Integer page,
				@RequestParam(value ="linhaporPagina", defaultValue = "24")Integer linhaporPagina,
				@RequestParam(value ="ordena", defaultValue = "nome")String ordena,//ordena por name
				@RequestParam(value ="direction", defaultValue = "ASC")String direction){ //ordena ascendente
			
			//exemplo da requisição localhost:8080/produtos/?nome=computador&categorias=1,3,4
			//chama o metodo search crair uma classe auxiliar chamada no pacote resource.utils chamada URL para pegar a string da requisiçao e colocar numa lista de inteiros separando a virgula e quebrar
			
			//chama funçao decodeParam na classe url para tirar espaços
			String nomeDecoded = URL.decodeParam(nome);
			//lista recebe o metodo da classe url chamado decodeIntList que recebe parametro da url chamado categorias  da requisção ja separada com as listas de inteiros
			List<Integer> ids = URL.decodeIntList(categorias);
			//o nome pode vir com espaçoes em branco usar trim ex TV LED é TV%20Led fazer metod na classe URL para tirar espaços
			Page<Produto> lista = service.search(nome, ids, page, linhaporPagina, ordena, direction);
			Page<ProdutoDTO> listaDTO = lista.map(obj -> new ProdutoDTO(obj)); 
			return ResponseEntity.ok().body(listaDTO); //conferir chamando localhost8080/produtos/page
			//testar 3 por pagina localhost:8080/produtos/page?linhaporPagina=3
			//localhost:8080/produtos/page?linhaporPagina=3&page=1 segunda pagina
		}
	
	

}
