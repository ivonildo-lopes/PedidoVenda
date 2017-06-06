package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Categoria;
import com.algaworks.pedidovenda.repository.filter.CategoriaParaPesquisa;
import com.algaworks.pedidovenda.service.CategoriaService;

@Named
@ViewScoped
public class PesquisaCategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaService categoriaService;

	private Categoria categoriaSelecionado;

	private List<Categoria> listaCategoria = new ArrayList<Categoria>();

	private CategoriaParaPesquisa categoriaParaPesquisa;

	public PesquisaCategoriaBean() {
		categoriaParaPesquisa = new CategoriaParaPesquisa();
	}

	public void pesquisar() {
		listaCategoria = categoriaService.listarCategorias(categoriaParaPesquisa);
	}
	
	public void remover(Categoria categoria){
		categoriaService.remover(categoria);
		listaCategoria.remove(categoria);
	}
	
//	public double porValorPendente(Categoria categoria){
//		return categoriaService.porValorPendente(categoria);
//	}

	// GET AND SET
	public List<Categoria> getListaCategoria() {
		return listaCategoria;
	}

	public void setListaCategoria(List<Categoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	public CategoriaParaPesquisa getCategoriaParaPesquisa() {
		return categoriaParaPesquisa;
	}

	public void setCategoriaParaPesquisa(CategoriaParaPesquisa categoriaParaPesquisa) {
		this.categoriaParaPesquisa = categoriaParaPesquisa;
	}

	public Categoria getCategoriaSelecionado() {
		return categoriaSelecionado;
	}
}
