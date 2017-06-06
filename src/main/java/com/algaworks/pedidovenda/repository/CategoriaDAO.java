package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.pedidovenda.model.Categoria;

public class CategoriaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public Categoria salvar(Categoria categoria){
		return manager.merge(categoria);
	}
	
	public void remover(Categoria categoria){
		manager.remove(categoria);
	}
	
	public Categoria porId(Long id) {
		return manager.find(Categoria.class, id);
	}
	
	//lista as categorias
	public List<Categoria> raizes() {
		return manager.createQuery("from Categoria where categoriaPai is null ORDER BY descricao ASC", 
				Categoria.class).getResultList();
	}
	
	//lista as subcategorias
	public List<Categoria> subcategoriasDe(Categoria categoriaPai) {
		return manager.createQuery("from Categoria where categoriaPai = :raiz ORDER BY descricao ASC", 
				Categoria.class).setParameter("raiz", categoriaPai).getResultList();
	}
	
	public List<Categoria> subcategoriasDe(String descricao) {
		return manager.createQuery("from Categoria where upper(descricao) like :descricao and categoriaPai is not null ORDER BY descricao ASC", 
				Categoria.class).setParameter("descricao","%"+descricao.toUpperCase()+"%").getResultList();
	}
	
}