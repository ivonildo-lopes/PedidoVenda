package com.algaworks.pedidovenda.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Categoria;
import com.algaworks.pedidovenda.repository.CategoriaDAO;
import com.algaworks.pedidovenda.repository.filter.CategoriaParaPesquisa;
import com.algaworks.pedidovenda.util.jpa.Transactional;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

public class CategoriaService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CategoriaDAO categoriaDAO;
	
	@Transactional
	public Categoria salvar(Categoria categoria){
		categoria = categoriaDAO.salvar(categoria);
		
		if(categoria != null){
			FacesUtil.InfoMessage("Categoria/Sub-Categoria salva com sucesso!");
		}else{
			FacesUtil.ErrorMessage("Não foi possível salvar");
		}
		
		return categoria;
	}
	
	@Transactional
	public void remover(Categoria categoria){
		categoriaDAO.remover(categoria);
	}
	
	public List<Categoria> listarCategorias(){
		return categoriaDAO.raizes();
	}
	
	public List<Categoria> listarCategorias(CategoriaParaPesquisa categoriaParaPesquisa){
		return categoriaDAO.subcategoriasDe(categoriaParaPesquisa.getDescricao());
	}

}
