package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.model.EnderecoEntrega;
import com.algaworks.pedidovenda.model.FormaPagamento;
import com.algaworks.pedidovenda.model.ItemPedido;
import com.algaworks.pedidovenda.model.ItemPedido2;
import com.algaworks.pedidovenda.model.Pedido2;
import com.algaworks.pedidovenda.model.Produto2;
import com.algaworks.pedidovenda.model.Usuario;
import com.algaworks.pedidovenda.service.ClienteService;
import com.algaworks.pedidovenda.service.PedidoService2;
import com.algaworks.pedidovenda.service.ProdutoService2;
import com.algaworks.pedidovenda.service.UsuarioService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;
import com.algaworks.pedidovenda.validation.PedidoAlteradoEvent2;
import com.algaworks.pedidovenda.validation.PedidoEdicao;

@Named
@ViewScoped
public class CadastroPedidoBean2 implements Serializable {

	private static final long serialVersionUID = 1L;

	@Produces
	@PedidoEdicao
	private Pedido2 pedido;

	private List<Usuario> vendedores;

	@Inject
	private PedidoService2 pedidoService;

	@Inject
	private UsuarioService usuarioService;

	@Inject
	private ClienteService clienteService;

	@Inject
	private ProdutoService2 produtoService;

	private Produto2 produtoLinhaEditavel;


	public CadastroPedidoBean2() {
		Limpar();
	}

	public void salvar() {
		try {
			this.pedido = this.pedidoService.salvar(pedido);
		} finally {
			this.pedido.adicionaItemVazio();
		}
	}

	public List<Cliente> completarCliente(String nome) {
		return clienteService.listarPorNome(nome);
	}

	public void Limpar() {
		pedido = new Pedido2();
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

	public List<Produto2> completarProduto(String nome) {
		return this.produtoService.porNome(nome.toUpperCase());
	}

	public void carregarProdutoLinhaEditavel() {
		ItemPedido2 item = this.pedido.getItens().get(0);
		if (this.produtoLinhaEditavel != null) {
//			if (this.existeItemComProduto(this.produtoLinhaEditavel)) {
//				FacesUtil.AvisoMessage("Ja existe um item no pedido com o produto informado");
//			} else {
				item.setProduto(this.produtoLinhaEditavel);
				//item.setValorUnitario(this.produtoLinhaEditavel.getValorUnitario());
				item.setValorM2(this.produtoLinhaEditavel.getValorM2());
				
				this.pedido.adicionaItemVazio();
				this.produtoLinhaEditavel = null;
				this.pedido.recalcularValorTotal();
			//}
		}
	}

	private boolean existeItemComProduto(Produto2 produto) {
		boolean existeItem = false;

		for (ItemPedido2 item : this.getPedido().getItens()) {
			if (produto.equals(item.getProduto())) {
				existeItem = true;
				break;
			}
		}

		return existeItem;
	}

	//modificar nome
	public void atualizarQuantidade(ItemPedido2 item, int linha) {

//		if (item.getLargura() < 1.0) {
//			if (linha == 0) {
//				item.setLargura(1.0);
//			} else {
//				this.getPedido().getItens().remove(linha);
//			}
//		}
		
		if (item.getQuantidade() < 1) {
			if (linha == 0) {
				item.setQuantidade(1);
			} else {
				this.getPedido().getItens().remove(linha);
			}
		}

		this.pedido.recalcularValorTotal();

	}

	public void pedidoAlterado(@Observes PedidoAlteradoEvent2 event) {
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
	public Pedido2 getPedido() {
		return pedido;
	}

	public void setPedido(Pedido2 pedido) {
		this.pedido = pedido;
	}

	public List<Usuario> getVendedores() {
		return vendedores;
	}

	public Produto2 getProdutoLinhaEditavel() {
		return produtoLinhaEditavel;
	}

	public void setProdutoLinhaEditavel(Produto2 produtoLinhaEditavel) {
		this.produtoLinhaEditavel = produtoLinhaEditavel;
	}

}