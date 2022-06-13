package com.gillianocampos.cursospringangular.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gillianocampos.cursospringangular.entities.ItemPedido;
import com.gillianocampos.cursospringangular.entities.PagamentoComBoleto;
import com.gillianocampos.cursospringangular.entities.Pedido;
import com.gillianocampos.cursospringangular.entities.enums.EstadoPagamento;
import com.gillianocampos.cursospringangular.repositories.ItemPedidoRepository;
import com.gillianocampos.cursospringangular.repositories.PagamentoRepository;
import com.gillianocampos.cursospringangular.repositories.PedidoRepository;
import com.gillianocampos.cursospringangular.services.exceptions.ExcecaoObjetoNaoEncontrado;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	// injetar boletoService
	@Autowired
	private BoletoService boletoService;

	// pagametoRepository para salvar la em baixo o pagamento
	@Autowired
	private PagamentoRepository pagamentoRepository;

	// injeçao produto para buscar o preco pois vira so o id dele na requisição
	@Autowired
	private ProdutoService produtoService;

	// injeção dos items de pedido
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;

	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		// se o objeto nção existir lança a exceção
		return obj.orElseThrow(() -> new ExcecaoObjetoNaoEncontrado(
				"Objeto não encontrado! Id " + id + ", Tipo: " + Pedido.class.getName()));
	}

	// metodo para inserir um pedido que tem id e instante como atributos
	@Transactional // para salvar tudo ou nada
	public Pedido inserir(Pedido obj) {
		// seta id desse novo objeto para nulo para garantir que estou inserindo um novo
		// pedido
		obj.setId(null);

		// seta o instante desse pedido
		obj.setInstante(new Date());

		obj.setCliente(clienteService.buscar(obj.getCliente().getId()));
		
		// classe pagamento id é o mesmo do idpedido que sera utomaticamente feito pelo
		// jpa e estado do pagamento pendente
		// inserindo pedido agora entao o pagamento é pendente
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);

		// associação de mao dupla 1 para 1 pagamento tem que conhecer o pedido dele
		obj.getPagamento().setPedido(obj);

		// se o pagamento for do tipo(instanceof verifica se é do tipo)
		// pagamentocoboleto vou gerar data para ele
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			// crio variavel do tipo pagamentocomboleto recebendo casting do
			// obj.getpagamento()
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			// crio uma classe chamada BoletoService e nela um metodo
			// preencherPagamentoComBoleto
			// este metodo paramento passando o pagamento pgto e o instante do meu pedido
			// este metodo vai ter que preencher a data de vencimento com 1 semana depois
			// por exemplo do instante do pedido
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
			// injetar um boletoService la em cima
		}
		// salvar pedido no banco
		obj = repo.save(obj);

		// salvar pagamento antes criar instancia de PagamentoRepository
		pagamentoRepository.save(obj.getPagamento());

		// falta salvar itensdepedido
		// atributos de itens de pedido desconto, quantidade e preço
		// desconto vai ser zero pois nõa tem desconto setar para zero
		// quantidade ja vai vir na requisição json ja esta instanciada
		// preco eu tenho que copiar o preco do produto associado ao item
		// por fim associar o pedido e o produto neste item

		// percorre todos itens de pedido associados ao meu obj.getitens
		for (ItemPedido ip : obj.getItens()) {
			// para cada item vou fazer desconto zero, quantidade ja vai vir na requisição
			// json
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.buscar(ip.getProduto().getId()));
			
			// preco vou ter que buscar no banco pois na requisição so vira o id. para isso
			// fazer injeção do produtoService la em cima para chamar o metodo buscar em
			// ProdutoService
			// seto o preco do item ip do produto com base no id do produto que veio na
			// requisiçao
			// pego esse id e busco no banco de dados o produto inteiro
			// de posse do produto na mao eu chama no fim o getPreco()
			ip.setPreco(ip.getProduto().getPreco());
			// associo esse item de pedido como pedido que estou inserindo que é o obj
			ip.setPedido(obj);
		}
		// salvar os itens mas antes incluir a injeção de dependendia la em cima
		// o repositoy é capaz de salvar uma lista no caso obj.getItens()
		itemPedidoRepository.saveAll(obj.getItens());

		//para imprimir o pedido na tela
		// apaguei para agora chamar o envio de email System.out.println(obj);
		
		//chama o metodo sendorder.. para enviar email
		emailService.sendOrderConfirmationEmail(obj);
		
		// salvei pedido,pagamento, e itens agora retornar o obj
		return obj;
		
		//testar no postman dar um post para inserir um pedido
		/*{
			 "cliente" : {"id" : 1},
			 "enderecoDeEntrega" : {"id" : 1},
			 "pagamento" : {
			 "numeroDeParcelas" : 10,
			 "@type": "pagamentoComCartao"
			 },
			 "itens" : [
			 {
			 "quantidade" : 2,
			 "produto" : {"id" : 3}
			 },
			 {
			 "quantidade" : 1,
			 "produto" : {"id" : 1}
			 }
			 ]
			}*/
	}

}
