package com.gillianocampos.cursospringangular.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gillianocampos.cursospringangular.entities.Categoria;
import com.gillianocampos.cursospringangular.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
	//implementar metodo search para ser usado em ProdutoService
	//esse metodo vai consultar nomes que serao digitados e trazer a consulta
	//porem esse metodo não tem no repository pronto é preciso fazer uma consulta jpql para implemtear o metodo
	//usar a anotação @Query para jogar a variavel nome do metodo no valor LIKE %:nome% do jpql é so colocar @Param("nomequefoidadonoJPQL") 
	@Transactional(readOnly=true) //para dizer que é apenas uma consulta nãp precisa fazer transação no banco
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
			Page<Produto> findDistinctByNomeContainingAndCategoriasIn(
			@Param("nome") String nome,
			@Param("categorias") List<Categoria> categorias,
			Pageable pageRequest);
//o jpa tem uns nomes de metodos ja prontos que não precisa de fazer o @query so da uma pesquisada nos metodos la na documentação do springData Query
}
