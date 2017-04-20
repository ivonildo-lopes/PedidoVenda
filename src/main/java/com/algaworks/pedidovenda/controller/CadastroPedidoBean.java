package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.model.EnderecoEntrega;
import com.algaworks.pedidovenda.model.FormaPagamento;
import com.algaworks.pedidovenda.model.ItemPedido;
import com.algaworks.pedidovenda.model.Parcela;
import com.algaworks.pedidovenda.model.Pedido;
import com.algaworks.pedidovenda.model.Produto;
import com.algaworks.pedidovenda.model.Usuario;
import com.algaworks.pedidovenda.repository.ParcelaDAO;
import com.algaworks.pedidovenda.service.ClienteService;
import com.algaworks.pedidovenda.service.PedidoService;
import com.algaworks.pedidovenda.service.ProdutoService;
import com.algaworks.pedidovenda.service.UsuarioService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;
import com.algaworks.pedidovenda.validation.PedidoAlteradoEvent;
import com.algaworks.pedidovenda.validation.PedidoEdicao;
import com.algaworks.pedidovenda.validation.SKU;

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Produces
	@PedidoEdicao
	private Pedido pedido;

	private List<Usuario> vendedores;

	@Inject
	private PedidoService pedidoService;

	@Inject
	private UsuarioService usuarioService;

	@Inject
	private ClienteService clienteService;

	@Inject
	private ProdutoService produtoService;

	private Produto produtoLinhaEditavel;

	@SKU
	private String sku;

	@Inject
	private ParcelaDAO parcelaDAO;

	private List<Parcela> parcelas = new ArrayList<>();

	// @PostConstruct
	// public void init(){
	// parcelas = parcelaDAO.porPedido(pedido);
	// }

	public CadastroPedidoBean() {
		Limpar();
	}

	public void salvar() {
		try {

			if (pedidoService.verificaDividaPendentePorCliente(pedido) > 0) {
				FacesUtil.AvisoMessage("O cliente ainda possui divida pendente");
			}
			this.pedido = this.pedidoService.salvar(pedido);
		} finally {
			this.pedido.adicionaItemVazio();
		}
	}

	public List<Cliente> completarCliente(String nome) {
		return clienteService.listarPorNome(nome);
	}

	public void Limpar() {
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());

	}

	/**
	 * Metodo responsavel por carregar os vendedores antes da pagina ser
	 * renderizada
	 */

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			this.vendedores = usuarioService.listaVendedores();

			this.pedido.adicionaItemVazio();

			this.recalcularPedido();

			parcelas = parcelaDAO.porPedido(pedido);
		}
	}

	public FormaPagamento[] getFormasPagemento() {
		return FormaPagamento.values();
	}

	public boolean isEditando() {
		return this.pedido.getId() != null;
	}

	public void recalcularPedido() {

		if (this.pedido != null) {
			this.pedido.recalcularValorTotal();
		}

	}

	public List<Produto> completarProduto(String nome) {
		return this.produtoService.porNome(nome.toUpperCase());
	}

	public void carregarProdutoLinhaEditavel() {
		ItemPedido item = this.pedido.getItens().get(0);
		if (this.produtoLinhaEditavel != null) {
			if (this.existeItemComProduto(this.produtoLinhaEditavel)) {
				FacesUtil.AvisoMessage("Ja existe um item no pedido com o produto informado");
			} else {
				item.setProduto(this.produtoLinhaEditavel);
				item.setValorUnitario(this.produtoLinhaEditavel.getValorUnitario());

				this.pedido.adicionaItemVazio();
				this.produtoLinhaEditavel = null;
				this.sku = null;
				this.pedido.recalcularValorTotal();
			}
		}
	}

	private boolean existeItemComProduto(Produto produto) {
		boolean existeItem = false;

		for (ItemPedido item : this.getPedido().getItens()) {
			if (produto.equals(item.getProduto())) {
				existeItem = true;
				break;
			}
		}

		return existeItem;
	}

	public void carregarProdutoPorSKU() {
		if (StringUtils.isNotEmpty(this.sku)) {
			this.produtoLinhaEditavel = this.produtoService.porSku(this.sku);
			this.carregarProdutoLinhaEditavel();
		}

	}

	public void atualizarQuantidade(ItemPedido item, int linha) {

		if (item.getQuantidade() < 1) {
			if (linha == 0) {
				item.setQuantidade(1);
			} else {
				this.getPedido().getItens().remove(linha);
			}
		}

		this.pedido.recalcularValorTotal();

	}

	public void pedidoAlterado(@Observes PedidoAlteradoEvent event) {
		this.pedido = event.getPedido();
	}

	public boolean pagamentoCartao() {
		boolean retorno = false;

		if (pedido.getFormaPagamento() == FormaPagamento.CARTAO_CREDITO)
			retorno = true;
		return retorno;

	}

	public Date dataHoje() {
		return new Date();
	}

	// get and set
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Usuario> getVendedores() {
		return vendedores;
	}

	public Produto getProdutoLinhaEditavel() {
		return produtoLinhaEditavel;
	}

	public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
		this.produtoLinhaEditavel = produtoLinhaEditavel;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public List<Parcela> getParcelas() {
		return parcelas;
	}
}