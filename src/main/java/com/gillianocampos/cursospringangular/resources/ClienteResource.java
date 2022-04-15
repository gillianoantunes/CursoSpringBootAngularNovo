package com.gillianocampos.cursospringangular.resources;


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

import com.gillianocampos.cursospringangular.dto.ClienteDTO;
import com.gillianocampos.cursospringangular.entities.Cliente;
import com.gillianocampos.cursospringangular.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	

	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id){ 		
		Cliente obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);		
	}
	
	
		@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
		public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id){
			Cliente obj = service.fromDTO(objDTO); 
			obj.setId(id); 
			obj = service.update(obj);
			return ResponseEntity.noContent().build();
		}
		
		@RequestMapping(value= "/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<Void> delete (@PathVariable Integer id){
			service.delete(id); 
			return ResponseEntity.noContent().build();
		}
		
		
		@RequestMapping(method = RequestMethod.GET)
		public ResponseEntity<List<ClienteDTO>> findAll(){
			List<Cliente> lista = service.findAll();
			List<ClienteDTO> listaDTO = lista.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
			return ResponseEntity.ok().body(listaDTO);
		}
		
		
		@RequestMapping(value = "/page" ,method = RequestMethod.GET)
		public ResponseEntity<Page<ClienteDTO>> findPage(
				@RequestParam(value ="page", defaultValue = "0") Integer page,
				@RequestParam(value ="linhaporPagina", defaultValue = "24")Integer linhaporPagina,
				@RequestParam(value ="ordena", defaultValue = "name")String ordena,
				@RequestParam(value ="direction", defaultValue = "ASC")String direction){
			Page<Cliente> lista = service.findPage(page, linhaporPagina, ordena, direction); 
			Page<ClienteDTO> listaDTO = lista.map(obj -> new ClienteDTO(obj));
			return ResponseEntity.ok().body(listaDTO); 
			
		}
}
