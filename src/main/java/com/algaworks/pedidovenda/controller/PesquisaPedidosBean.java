package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Pedido;
import com.algaworks.pedidovenda.model.StatusPedido;
import com.algaworks.pedidovenda.repository.filter.PedidoParaPesquisa;
import com.algaworks.pedidovenda.service.PedidoService;

@Named
@ViewScoped
public class PesquisaPedidosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private PedidoParaPesquisa pedidoParaPesquisa;

	private List<Pedido> pedidosFiltrados;

	@Inject
	private PedidoService pedidoService;
	
	private Pedido pedidoSelecionado;
	
	public PesquisaPedidosBean() {
		pedidoParaPesquisa = new PedidoParaPesquisa();
		pedidosFiltrados = new ArrayList<>();
	}
	
	
	
	public void pesquisar() {
		this.pedidosFiltrados = pedidoService.filtrados(pedidoParaPesquisa);
	}
	
	public StatusPedido[] getStatuses(){
		return StatusPedido.values();
	}

	public Boolean vefiricaPedidoQuitado(Pedido pedido){
		if(pedidoService.verificaPedidoQuitado(pedido) > 0)
			return false;
		
		return true;
	}
	
	public Pedido porId(Pedido pedido){
		pedidoSelecionado = pedidoService.porId(pedido); 
		return pedidoSelecionado;
	}
	
	// get and set
	public PedidoParaPesquisa getPedidoParaPesquisa() {
		return pedidoParaPesquisa;
	}

	public void setPedidoParaPesquisa(PedidoParaPesquisa pedidoParaPesquisa) {
		this.pedidoParaPesquisa = pedidoParaPesquisa;
	}

	public List<Pedido> getPedidosFiltrados() {
		return pedidosFiltrados;
	}

	public void setPedidosFiltrados(List<Pedido> pedidosFiltrados) {
		this.pedidosFiltrados = pedidosFiltrados;
	}

	public PedidoService getPedidoService() {
		return pedidoService;
	}

	public void setPedidoService(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}



	public Pedido getPedidoSelecionado() {
		return pedidoSelecionado;
	}



	public void setPedidoSelecionado(Pedido pedidoSelecionado) {
		this.pedidoSelecionado = pedidoSelecionado;
	}
	
	

}