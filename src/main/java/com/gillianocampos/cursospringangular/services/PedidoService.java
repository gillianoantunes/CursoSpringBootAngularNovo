package com.gillianocampos.cursospringangular.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gillianocampos.cursospringangular.entities.Pedido;
import com.gillianocampos.cursospringangular.repositories.PedidoRepository;
import com.gillianocampos.cursospringangular.services.exceptions.ExcecaoObjetoNaoEncontrado;


@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		//se o objeto nção existir lança a exceção
		return obj.orElseThrow(() -> new ExcecaoObjetoNaoEncontrado("Objeto não encontrado! Id " + id + 
												", Tipo: " + Pedido.class.getName()));
		}
}
