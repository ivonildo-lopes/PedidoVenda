package com.algaworks.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Pedido;
import com.algaworks.pedidovenda.model.StatusPedido;
import com.algaworks.pedidovenda.repository.ParcelaDAO;
import com.algaworks.pedidovenda.repository.PedidoDAO;
import com.algaworks.pedidovenda.util.jpa.Transactional;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

public class CancelaPedidoService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private PedidoDAO pedidoDAO;

	@Inject
	private EstoqueService estoqueService;

	@Inject
	private ParcelaDAO parcelaDAO;

	@Transactional
	public Pedido cancelar(Pedido pedido) {
		pedido = this.pedidoDAO.porId(pedido.getId());

		if (pedido.isNaoCancelavel()) {
			throw new NegocioException("Pedido nao pode ser cancelado  no  Status" + pedido.getStatus().getDescricao());
		}

		if (parcelaDAO.verificaPedidopago(pedido)) {
			throw new NegocioException("Pedido nao pode ser cancelado  ja existe parcela paga");
		}

		if (pedido.isEmitido()) {
			this.estoqueService.retornarItensEstoque(pedido);
		}

		pedido.setStatus(StatusPedido.CANCELADO);
		pedido = pedidoDAO.salvar(pedido);

		FacesUtil.InfoMessage("Pedido Cancelado");
		return pedido;
	}

}
