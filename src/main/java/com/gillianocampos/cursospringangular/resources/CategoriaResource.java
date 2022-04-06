package com.gillianocampos.cursospringangular.resources;



import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gillianocampos.cursospringangular.entities.Categoria;
import com.gillianocampos.cursospringangular.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	//Buscar UMA CATEGORIA DIRETO NO BANCO H2
	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id){ //? pode ser qualquer tipo		
		Categoria obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);		
	}
	
	@RequestMapping(method = RequestMethod.POST) 
	public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
		obj = service.inserir(obj); // o save do service retorna um obj
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id){
		obj.setId(id); //só para garantir que objeto esta com id da requisição
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

}
