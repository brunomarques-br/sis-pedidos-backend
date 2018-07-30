package br.com.sis.pedidos.sistemapedidos;

import br.com.sis.pedidos.sistemapedidos.domain.*;
import br.com.sis.pedidos.sistemapedidos.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class SistemaPedidosApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public static void main(String[] args) {
        SpringApplication.run(SistemaPedidosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");

        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 20.00);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll(Arrays.asList(cat1));

        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p2.getCategorias().addAll(Arrays.asList(cat1));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null, "Uberlândia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        Cliente cli1 = new Cliente(null, "Maria Silva", "mariasilva@gmail.com", "12345678909", TipoCliente.PESSOA_FISICA);

        Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "75221468", cli1, c1);
        Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Apto 808", "Centro", "38777012", cli1, c2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

        cli1.getTelefones().addAll(Arrays.asList("33251456", "698541266"));

        clienteRepository.saveAll(Arrays.asList(cli1));
        enderecoRepository.saveAll(Arrays.asList(e1, e2));

        Pedido pedido_1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
        Pedido pedido_2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

        Pagamento pgt_1 = new PagamentoComCartao(EstadoPagamento.QUITADO, pedido_1, 6);
        pedido_1.setPagamento(pgt_1);

        Pagamento pgt_2 = new PagamentoComBoleto(EstadoPagamento.PENDENTE, pedido_2, sdf.parse("20/10/2017 00:00"), null);
        pedido_2.setPagamento(pgt_2);

        cli1.getPedidos().addAll(Arrays.asList(pedido_1, pedido_2));

        pedidoRepository.saveAll(Arrays.asList(pedido_1, pedido_2));
        pagamentoRepository.saveAll(Arrays.asList(pgt_1, pgt_2));

        ItemPedido item_pedido_1 = new ItemPedido(pedido_1, p1, 0.00, 1, 2000.00);
        ItemPedido item_pedido_2 = new ItemPedido(pedido_1, p3, 0.00, 2, 80.00);
        ItemPedido item_pedido_3 = new ItemPedido(pedido_2, p2, 90.00, 1, 800.00);

        pedido_1.getItens().addAll(Arrays.asList(item_pedido_1, item_pedido_2));
        pedido_2.getItens().addAll(Arrays.asList(item_pedido_3));

        p1.getItens().addAll(Arrays.asList(item_pedido_1));
        p2.getItens().addAll(Arrays.asList(item_pedido_2));
        p3.getItens().addAll(Arrays.asList(item_pedido_3));

        itemPedidoRepository.saveAll(Arrays.asList(item_pedido_1, item_pedido_2, item_pedido_3));



    }
}
