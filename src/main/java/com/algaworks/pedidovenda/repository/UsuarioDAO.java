package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.algaworks.pedidovenda.model.Usuario;

public class UsuarioDAO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Usuario porId(Long id){
		return this.manager.find(Usuario.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> listarVendedores(){
		String jpql = "from Usuario ORDER BY nome ASC";
		Query query = manager.createQuery(jpql,Usuario.class);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> pesquisar(String nome){
		String jpql = "from Usuario u where upper(u.nome) like :nome ORDER BY nome ASC";
		Query query = manager.createQuery(jpql,Usuario.class);
		query.setParameter("nome","%" + nome.toUpperCase() + "%");
		return query.getResultList();
	}
	
	public Usuario salvar(Usuario usuario){
		return manager.merge(usuario);
	}
}
