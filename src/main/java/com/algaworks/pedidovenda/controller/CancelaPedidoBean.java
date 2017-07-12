package com.algaworks.pedidovenda.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Pedido;
import com.algaworks.pedidovenda.service.CancelaPedidoService;
import com.algaworks.pedidovenda.validation.PedidoAlteradoEvent;
import com.algaworks.pedidovenda.validation.PedidoEdicao;

@Named
@RequestScoped
public class CancelaPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CancelaPedidoService cancelaPedidoService;

	@Inject
	private Event<PedidoAlteradoEvent> pedidoAlteradoEvent;
	
	@Inject
	@PedidoEdicao
	private Pedido pedido;

	public void cancelarPedido() {
		
		this.pedido = this.cancelaPedidoService.cancelar(this.pedido);
		this.pedidoAlteradoEvent.fire(new PedidoAlteradoEvent(this.pedido) );

	}
}
