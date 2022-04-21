package com.gillianocampos.cursospringangular.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gillianocampos.cursospringangular.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	//metodo para fazer busca por email e ver se nao é repetido
	//retorna um cliente e se eu ponho nome findBynomedocampo o springdata ja faz esse metodo pra nos de busca por email
	//recebe uma String email como parametro
	//(readOnly = true) para falar que nao necessita de serbtransação de banco de dados isso faz ela ficar mais rapido
	//agora ir no ClienteInsertValidator e injetar o ClienteRepository e um if uma logica pra testar email
	@Transactional(readOnly = true)
	Cliente findByEmail(String email);

}
