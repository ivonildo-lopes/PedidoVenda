package com.algaworks.pedidovenda.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Pedido;
import com.algaworks.pedidovenda.model.StatusPedido;
import com.algaworks.pedidovenda.repository.PedidoDAO;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class EmissaoPedidoService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Inject
	private PedidoService pedidoService;
	
	@Inject
	private EstoqueService estoqueService;
	
	@Inject
	private PedidoDAO pedidoDAO;

	@Transactional
	public Pedido emitir(Pedido pedido) {

		
		pedido = this.pedidoService.salvar(pedido);

		if (pedido.isNaoEmissivel()) {
			throw new NegocioException(
					"Pedido não pode ser emitido com status: "
							+ pedido.getStatus().getDescricao());
		}
		
		this.estoqueService.baixarItensEstoque(pedido);
		
		pedido.setStatus(StatusPedido.EMITIDO);
		pedido.setDataEmissao(new Date());
		pedido = this.pedidoDAO.salvar(pedido);

		return pedido;
	}

}
