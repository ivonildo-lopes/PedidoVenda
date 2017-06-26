package com.algaworks.pedidovenda.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Fornecedor;
import com.algaworks.pedidovenda.model.TipoPessoa;
import com.algaworks.pedidovenda.service.FornecedorService;

@Named
@ViewScoped
public class CadastroFornecedorBean implements Serializable {

	private static final long serialVersionUID = 1L;


	private Fornecedor fornecedor;
	
	private boolean fisica;
	
	
	@Inject
	private FornecedorService fornecedorService;
	
	public CadastroFornecedorBean() {
		limpar();
	}

	public void limpar() {
		fornecedor = new Fornecedor();
	}
	
	public void salvar(){
		this.fornecedor = this.fornecedorService.salvar(fornecedor);
		limpar();
	}
	
	public boolean verificaEdicao(){
		return this.fornecedor.getId() != null;
	}

	public String formataMascara(){
		String mascara = "999.999.999-99";
		if(this.fornecedor.isPessoaFisica())
			mascara = "999.999.999-99";
		else
			mascara = "99.999.999/9999-99";
		
		return mascara;
	}
	
	public boolean teste(){
		this.fisica = this.fornecedor.isPessoaFisica();
		return fisica;
	}
	
	public TipoPessoa[] getTipoPessoaFisica(){
		return TipoPessoa.values();
	}
	
	//get and set
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public boolean isFisica() {
		return fisica;
	}

	public void setFisica(boolean fisica) {
		this.fisica = fisica;
	}
	
	
}
