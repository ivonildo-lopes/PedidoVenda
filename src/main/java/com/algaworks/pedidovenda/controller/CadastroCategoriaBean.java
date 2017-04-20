package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Categoria;
import com.algaworks.pedidovenda.service.CategoriaService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCategoriaBean implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Categoria categoria;
	
	//teste
	private String value;
	
	private Boolean adionarSubCategoria = false;
	private List<Categoria> listaCategoria = new ArrayList<>();
	
	@Inject
	private CategoriaService categoriaService;
	
	public CadastroCategoriaBean() {
		limpar();
	}
	
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			this.listaCategoria = categoriaService.listarCategorias();		
		}
	}

	public void limpar() {
		categoria = new Categoria();
	}
	
	public void salvar(){
		this.categoria = this.categoriaService.salvar(categoria);
		limpar();
	}
	
	public String editar(Categoria categoria){
		this.categoria = categoria;
		return "/categorias/cadastroCategoria.xhtml?faces-redirect=tre";
	}
	
	public boolean verificaEdicao(){
		return this.categoria.getId() != null;
	}

	//get and set
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Boolean getAdionarSubCategoria() {
		return adionarSubCategoria;
	}

	public void setAdionarSubCategoria(Boolean adionarSubCategoria) {
		this.adionarSubCategoria = adionarSubCategoria;
	}

	public List<Categoria> getListaCategoria() {
		return listaCategoria;
	}

	public void setListaCategoria(List<Categoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
