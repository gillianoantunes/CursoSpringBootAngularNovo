package com.gillianocampos.cursospringangular.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gillianocampos.cursospringangular.entities.Pagamento;

//só cria Repository da superclasse , não cria na subclasse
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
