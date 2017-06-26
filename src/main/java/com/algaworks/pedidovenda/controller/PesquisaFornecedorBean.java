package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Fornecedor;
import com.algaworks.pedidovenda.repository.filter.FornecedorParaPesquisa;
import com.algaworks.pedidovenda.service.FornecedorService;

@Named
@ViewScoped
public class PesquisaFornecedorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FornecedorService fornecedorService;

	private Fornecedor fornecedorSelecionado;

	private List<Fornecedor> listaFornecedor = new ArrayList<Fornecedor>();

	private FornecedorParaPesquisa fornecedorParaPesquisa;

	public PesquisaFornecedorBean() {
		fornecedorParaPesquisa = new FornecedorParaPesquisa();
	}

	public void pesquisar() {
		listaFornecedor = fornecedorService.pesquisar(fornecedorParaPesquisa);
	}

	public void remover(Fornecedor fornecedor) {
		fornecedorService.remover(fornecedor);
		listaFornecedor.remove(fornecedor);
	}

	// GET AND SET
	public List<Fornecedor> getListaFornecedor() {
		return listaFornecedor;
	}

	public void setListaFornecedor(List<Fornecedor> listaFornecedor) {
		this.listaFornecedor = listaFornecedor;
	}

	public FornecedorParaPesquisa getFornecedorParaPesquisa() {
		return fornecedorParaPesquisa;
	}

	public void setFornecedorParaPesquisa(FornecedorParaPesquisa fornecedorParaPesquisa) {
		this.fornecedorParaPesquisa = fornecedorParaPesquisa;
	}

	public Fornecedor getFornecedorSelecionado() {
		return fornecedorSelecionado;
	}
}
