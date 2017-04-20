package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.model.TipoPessoa;
import com.algaworks.pedidovenda.service.ClienteService;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Cliente cliente;
	
	
	@Inject
	private ClienteService clienteService;
	
	public CadastroClienteBean() {
		limpar();
	}

	public void limpar() {
		cliente = new Cliente();
	}
	
//	public void limparAssinatura() {
//		cliente.setAssinatura(null);
//	}
	
	public void salvar(){
		this.cliente = this.clienteService.salvar(cliente);
		limpar();
	}
	
	public String editar(Cliente cliente){
		this.cliente = cliente;
		//return "/produtos/CadastroProduto.xhtml?faces-redirect=true";
		return "/clientes/CadastroCliente.xhtml?faces-redirect=tre";
	}
	
	public boolean verificaEdicao(){
		return this.cliente.getId() != null;
	}

	public TipoPessoa[] getTipoPessoaFisica(){
		return TipoPessoa.values();
	}
	
	public Date dataHoje(){
		return new Date();
	}
	
	//get and set
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
