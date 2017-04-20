package com.algaworks.pedidovenda.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.FormaPagamento;
import com.algaworks.pedidovenda.model.Pedido;
import com.algaworks.pedidovenda.service.EmissaoPedidoService;
import com.algaworks.pedidovenda.service.PedidoService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;
import com.algaworks.pedidovenda.validation.PedidoAlteradoEvent;
import com.algaworks.pedidovenda.validation.PedidoEdicao;

@Named
@RequestScoped
public class EmissaoPedidoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	@PedidoEdicao
	private Pedido pedido;

	@Inject
	private EmissaoPedidoService emissaoPedidoService;

	@Inject
	private Event<PedidoAlteradoEvent> pedidoAlteradoEvent;

	@Inject
	private PedidoService pedidoService;

	public void emitirPedido() {

		if (pedido.getFormaPagamento() == FormaPagamento.CARTAO_CREDITO
				&& pedidoService.verificaDividaPendentePorCliente(pedido) > 0) {
		FacesUtil.ErrorMessage("Não é possivel emitir o pedido, cliente esta pendente");
		} else {
			this.pedido.removerItemVazio();

			try {
				this.pedido = this.emissaoPedidoService.emitir(this.pedido);
				// lançar um evento CDI
				this.pedidoAlteradoEvent.fire(new PedidoAlteradoEvent(
						this.pedido));
			} finally {
				this.pedido.adicionaItemVazio();
			}

		}

	}
}
