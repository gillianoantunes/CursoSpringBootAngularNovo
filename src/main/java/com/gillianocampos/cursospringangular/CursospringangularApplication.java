package com.gillianocampos.cursospringangular;

import java.text.SimpleDateFormat;
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
import com.gillianocampos.cursospringangular.entities.ItemPedido;
import com.gillianocampos.cursospringangular.entities.Pagamento;
import com.gillianocampos.cursospringangular.entities.PagamentoComBoleto;
import com.gillianocampos.cursospringangular.entities.PagamentoComCartao;
import com.gillianocampos.cursospringangular.entities.Pedido;
import com.gillianocampos.cursospringangular.entities.Produto;
import com.gillianocampos.cursospringangular.entities.enums.EstadoPagamento;
import com.gillianocampos.cursospringangular.entities.enums.TipoCliente;
import com.gillianocampos.cursospringangular.repositories.CategoriaRepository;
import com.gillianocampos.cursospringangular.repositories.CidadeRepository;
import com.gillianocampos.cursospringangular.repositories.ClienteRepository;
import com.gillianocampos.cursospringangular.repositories.EnderecoRepository;
import com.gillianocampos.cursospringangular.repositories.EstadoRepository;
import com.gillianocampos.cursospringangular.repositories.ItemPedidoRepository;
import com.gillianocampos.cursospringangular.repositories.PagamentoRepository;
import com.gillianocampos.cursospringangular.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursospringangularApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//instanciando categorias
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletronicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
	
		
		
		//instanciando produtos
		Produto produto1 = new Produto(null, "Computador", 2000.0);
		Produto produto2 = new Produto(null, "Impressora", 800.0);
		Produto produto3 = new Produto(null, "Mouse", 80.0);
		Produto produto4 = new Produto(null, "Mesa de escritório", 300.0);
		Produto produto5 = new Produto(null, "Toalha", 50.0);
		Produto produto6 = new Produto(null, "Colcha", 200.0);
		Produto produto7 = new Produto(null, "TV true color", 1200.0);
		Produto produto8 = new Produto(null, "Roçadeira", 800.0);
		Produto produto9 = new Produto(null, "Abajour", 100.0);
		Produto produto10 = new Produto(null, "Pendente", 180.0);
		Produto produto11 = new Produto(null, "Shampoo", 90.0);
		
		cat1.getProdutos().addAll(Arrays.asList(produto1,produto2,produto3));
		cat2.getProdutos().addAll(Arrays.asList(produto2,produto4));
		cat3.getProdutos().addAll(Arrays.asList(produto5,produto6));
		cat4.getProdutos().addAll(Arrays.asList(produto1,produto2,produto3,produto7));
		cat5.getProdutos().addAll(Arrays.asList(produto8));
		cat6.getProdutos().addAll(Arrays.asList(produto9,produto10));
		cat7.getProdutos().addAll(Arrays.asList(produto11));
		
		produto1.getCategorias().addAll(Arrays.asList(cat1));
		produto2.getCategorias().addAll(Arrays.asList(cat1,cat2,cat4));
		produto3.getCategorias().addAll(Arrays.asList(cat1,cat4));
		produto4.getCategorias().addAll(Arrays.asList(cat2));
		produto5.getCategorias().addAll(Arrays.asList(cat3));
		produto6.getCategorias().addAll(Arrays.asList(cat3));
		produto7.getCategorias().addAll(Arrays.asList(cat4));
		produto8.getCategorias().addAll(Arrays.asList(cat5));
		produto9.getCategorias().addAll(Arrays.asList(cat6));
		produto10.getCategorias().addAll(Arrays.asList(cat6));
		produto11.getCategorias().addAll(Arrays.asList(cat7));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
		
		produtoRepository.saveAll(Arrays.asList(produto1,produto2,produto3,produto4,produto5,produto6,
				produto7,produto8,produto9,produto10,produto11));
		
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
		
		
		//instanciar os pedidos
		//HH maisculos MM tambem
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		//quando instancia ele pede o pagamento, mudar pedido e tirar pagamento no construtor para tirar a obrigatoriedade pois relacionamento é 1 para 1
		Pedido ped1 = new Pedido(null,sdf.parse("30/09/2017 10:32"),cli1, end1);
		Pedido ped2 = new Pedido(null,sdf.parse("10/10/2017 19:35"),cli1, end2);
		
		//instanciar o pagamento abstract dando new com subclasses
		//nunca instancia pagamento diretamente abstract
		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		//agora adicionar este pagamento ao pedido1
		ped1.setPagamento(pag1);
		
		//data do pagamento esta null pq não foi pago ainda
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		//agora adicionar este pagamento ao pedido2
		ped2.setPagamento(pag2);
		
		//associar os clientes aos pedidos
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		//salvo o pedido primeiro pois ele é independente do pagamento
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		
		pagamentoRepository.saveAll(Arrays.asList(pag1,pag2));
		
		
		
		//instanciar items de pedido
		ItemPedido ip1 = new ItemPedido(ped1, produto1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, produto3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, produto2, 100.00, 1, 800.00);
		
		//associa os pedidos para conhecer seus items
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		//produto conhecer seus items tambem
		produto1.getItens().addAll(Arrays.asList(ip1));
		produto2.getItens().addAll(Arrays.asList(ip3));
		produto3.getItens().addAll(Arrays.asList(ip2));
		
		//salvar os items criando o repository
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}

}
