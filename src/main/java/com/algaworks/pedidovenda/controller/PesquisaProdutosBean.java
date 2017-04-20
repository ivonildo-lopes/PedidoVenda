package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Produto;
import com.algaworks.pedidovenda.repository.filter.ProdutoParaPesquisa;
import com.algaworks.pedidovenda.service.ProdutoService;

@Named
@ViewScoped
public class PesquisaProdutosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProdutoService produtos;
	
	private ProdutoParaPesquisa filtro;
	
	private List<Produto> produtosFiltrados = new ArrayList<Produto>();
	
	private Produto produtoSelecionado;
	
	public PesquisaProdutosBean() {
		filtro = new ProdutoParaPesquisa();
	}
	
	public void pesquisar() {
		this.produtosFiltrados = produtos.filtrados(filtro);
	}
	
	public void remover(){
		produtos.remover(produtoSelecionado);
		produtosFiltrados.remove(produtoSelecionado);
	}
	
	//get and set
	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public ProdutoParaPesquisa getFiltro() {
		return filtro;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}
	
	
}