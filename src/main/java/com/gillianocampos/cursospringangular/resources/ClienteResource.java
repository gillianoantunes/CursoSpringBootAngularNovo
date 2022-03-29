package com.gillianocampos.cursospringangular.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gillianocampos.cursospringangular.entities.Cliente;
import com.gillianocampos.cursospringangular.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	//INSERIR UMAS CATEGORIA DIRETO NO BANCO H2
	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id){ //? pode ser qualquer tipo		
		Cliente obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);		
	}

}
