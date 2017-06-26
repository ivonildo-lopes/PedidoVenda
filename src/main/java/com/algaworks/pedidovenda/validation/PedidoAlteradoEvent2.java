package com.algaworks.pedidovenda.validation;

import com.algaworks.pedidovenda.model.Pedido2;

public class PedidoAlteradoEvent2 {
	
	private Pedido2 pedido;
	
	public PedidoAlteradoEvent2(Pedido2 pedido){
	  this.pedido = pedido;	
	}
	
	public Pedido2 getPedido() {
		return pedido;
	}

}
