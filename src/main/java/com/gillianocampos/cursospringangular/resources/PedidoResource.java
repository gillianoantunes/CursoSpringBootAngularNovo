package com.gillianocampos.cursospringangular.resources;



import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gillianocampos.cursospringangular.dto.CategoriaDTO;
import com.gillianocampos.cursospringangular.entities.Categoria;
import com.gillianocampos.cursospringangular.entities.Pedido;
import com.gillianocampos.cursospringangular.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService service;
	

	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id){ 		
		Pedido obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);		
	}
	
	
	//metodo post copiei de categoria resource e alterei, nao vou usar dto pq pedido tem muitos dados associados, mas pode usar
	@RequestMapping(method = RequestMethod.POST) 
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) {
		obj = service.inserir(obj); // implementar o metodo inserir em pedidoService
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	
	}

}
