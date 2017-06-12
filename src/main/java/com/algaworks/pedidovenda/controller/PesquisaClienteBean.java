package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.repository.filter.ClienteParaPesquisa;
import com.algaworks.pedidovenda.service.ClienteService;

@Named
@ViewScoped
public class PesquisaClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteService clienteService;

	private Cliente clienteSelecionado;

	private List<Cliente> listaCliente = new ArrayList<Cliente>();

	private ClienteParaPesquisa clienteParaPesquisa;

	public PesquisaClienteBean() {
		clienteParaPesquisa = new ClienteParaPesquisa();
	}

	public void pesquisar() {
		listaCliente = clienteService.pesquisar(clienteParaPesquisa);
	}

	public void remover(Cliente cliente) {
		clienteService.remover(cliente);
		listaCliente.remove(cliente);
	}

	public double porValorPendente(Cliente cliente) {
		return clienteService.porValorPendente(cliente);
	}

	public boolean verificaPendenciaParcela(Cliente cliente){
		if(porValorPendente(cliente) > 0)
			return true;

		return false;			
		
	}

	// GET AND SET
	public List<Cliente> getListaCliente() {
		return listaCliente;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}

	public ClienteParaPesquisa getClienteParaPesquisa() {
		return clienteParaPesquisa;
	}

	public void setClienteParaPesquisa(ClienteParaPesquisa clienteParaPesquisa) {
		this.clienteParaPesquisa = clienteParaPesquisa;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}
}
