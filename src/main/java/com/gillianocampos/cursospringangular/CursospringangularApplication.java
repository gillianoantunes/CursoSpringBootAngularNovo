package com.gillianocampos.cursospringangular;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gillianocampos.cursospringangular.entities.Categoria;
import com.gillianocampos.cursospringangular.entities.Cidade;
import com.gillianocampos.cursospringangular.entities.Cliente;
import com.gillianocampos.cursospringangular.entities.Endereco;
import com.gillianocampos.cursospringangular.entities.Estado;
import com.gillianocampos.cursospringangular.entities.Produto;
import com.gillianocampos.cursospringangular.entities.enums.TipoCliente;
import com.gillianocampos.cursospringangular.repositories.CategoriaRepository;
import com.gillianocampos.cursospringangular.repositories.CidadeRepository;
import com.gillianocampos.cursospringangular.repositories.ClienteRepository;
import com.gillianocampos.cursospringangular.repositories.EnderecoRepository;
import com.gillianocampos.cursospringangular.repositories.EstadoRepository;
import com.gillianocampos.cursospringangular.repositories.ProdutoRepository;

@SpringBootApplication
public class CursospringangularApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRpository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursospringangularApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//instanciando categorias
		Categoria cat1 = new Categoria(null, "informática");
		Categoria cat2 = new Categoria(null, "escritório");
		
		//instanciando produtos
		Produto produto1 = new Produto(null, "computador", 2000.0);
		Produto produto2 = new Produto(null, "impressora", 800.0);
		Produto produto3 = new Produto(null, "mouse", 80.0);
		
		cat1.getProdutos().addAll(Arrays.asList(produto1,produto2,produto3));
		cat2.getProdutos().addAll(Arrays.asList(produto2));
		
		produto1.getCategorias().addAll(Arrays.asList(cat1));
		produto2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		produto3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		
		produtoRepository.saveAll(Arrays.asList(produto1,produto2,produto3));
		
		//instanciando estados
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		//instanciando cidades
		Cidade cid1 = new Cidade(null, "Uberlandia", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2,cid3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));
		
		//instanciando clientes primeiro que endereços e telefones pois não depende deles
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36415898741", TipoCliente.PESSOAFISICA);
		
		//telefones
		cli1.getTelefones().addAll(Arrays.asList("3257845211", "3154785546"));
		
		//intanciando endereços
		Endereco end1 = new Endereco(null,"Rua Flores", "300", "Apto 203", "Jardim", "38224772", cli1, cid1);
		Endereco end2 = new Endereco(null,"Avenida Matos", "105", "Sala 800", "Centro", "38545111", cli1, cid2);
	
		//cliente conhecer os endereços que são varios
		cli1.getEnderecos().addAll(Arrays.asList(end1,end2));
		
		//salva primeiro quem é independente . o cliente é independente de endereço o endereco tem 1 cliente é dependente
		clienteRpository.saveAll(Arrays.asList(cli1));
		
		enderecoRepository.saveAll(Arrays.asList(end1,end2));
		
	
	}

}
