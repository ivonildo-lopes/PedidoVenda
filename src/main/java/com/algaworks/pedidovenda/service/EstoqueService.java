package com.algaworks.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.ItemPedido;
import com.algaworks.pedidovenda.model.Pedido;
import com.algaworks.pedidovenda.repository.PedidoDAO;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class EstoqueService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PedidoDAO pedidoDAO;
	

	@Transactional
	public void baixarItensEstoque(Pedido pedido) {
		pedido = this.pedidoDAO.porId(pedido.getId());
		
		for(ItemPedido item : pedido.getItens()){
			item.getProduto().baixarEstoque(item.getQuantidade());
		}
	}


	public void retornarItensEstoque(Pedido pedido) {
		pedido = this.pedidoDAO.porId(pedido.getId());
		
		for(ItemPedido item : pedido.getItens()){
			item.getProduto().adicionarEstoque(item.getQuantidade());
		}
	}

}
