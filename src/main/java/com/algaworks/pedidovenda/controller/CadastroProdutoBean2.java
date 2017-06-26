package com.algaworks.pedidovenda.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Produto2;
import com.algaworks.pedidovenda.service.ProdutoService2;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoBean2 implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoService2 cadastroProdutoService;

	private Produto2 produto;

	public CadastroProdutoBean2() {
		limpar();
	}

	// esse metodo é chamado antes de renderizar a pagina
	public void inicializar() { // carrega as categorias
		if (FacesUtil.isNotPostback()) {
		}
	}


	private void limpar() {
		produto = new Produto2();
	}

	public void salvar() {
		this.produto = cadastroProdutoService.salvar(this.produto);
		limpar();

		FacesUtil.InfoMessage("Produto salvo com sucesso!");
	}

	public boolean verificaEdicao() {
		return this.produto.getId() != null;
	}

	// get and set
	public Produto2 getProduto() {
		return produto;
	}

	public void setProduto(Produto2 produto) {
		this.produto = produto;
	}

}