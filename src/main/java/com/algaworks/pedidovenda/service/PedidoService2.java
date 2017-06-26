package com.algaworks.pedidovenda.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Pedido2;
import com.algaworks.pedidovenda.repository.PedidoDAO2;
import com.algaworks.pedidovenda.repository.filter.PedidoParaPesquisa;
import com.algaworks.pedidovenda.util.jpa.Transactional;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@SuppressWarnings("serial")
public class PedidoService2 implements Serializable {

	@Inject
	private PedidoDAO2 pedidoDAO;
	
	public Pedido2 porId(Pedido2 pedido){
		return pedidoDAO.porId(pedido);
	}

	public List<Pedido2> filtrados(PedidoParaPesquisa pedido) {
		if (pedido.getNumeroDe() == null && pedido.getNumeroAte() == null) {
			pedido.setNumeroDe(null);
			pedido.setNumeroAte(null);
		}
		return pedidoDAO.filtrados(pedido);
	}

	@Transactional
	public Pedido2 salvar(Pedido2 pedido) {
		pedido.removerItemVazio();

		try {
			if (pedido.isNovo()) {
				pedido.setDataCriacao(new Date());
			}

			pedido.recalcularValorTotal();

			if (pedido.isNaoAlteravel()) {
				throw new NegocioException(
						"Pedido nao pode ser alterado no status "
								+ pedido.getStatus().getDescricao());
			}

			if (pedido.getItens().isEmpty()) {
				// FacesUtil.AvisoMessage("O Pedido deve ter pelo menos um item");
				throw new NegocioException(
						"O Pedido deve ter pelo menos um item");
			}

			if (pedido.isValorTotalNegativo()) {
				// FacesUtil.ErrorMessage("O valor total nao pode ser negativo");
				throw new NegocioException(
						"O valor total nao pode ser negativo");
			}

			pedido = this.pedidoDAO.salvar(pedido);

			FacesUtil.InfoMessage("Pedido salvo com sucesso!");

		} catch (Exception e) {
			FacesUtil.ErrorMessage("Erro ao tentar salva o pedido");
		}

		return pedido;

	}
	
}
