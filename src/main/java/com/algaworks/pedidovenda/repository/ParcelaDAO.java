package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.algaworks.pedidovenda.model.Parcela;
import com.algaworks.pedidovenda.model.Pedido;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class ParcelaDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	public Parcela porId(long id) {
		return manager.find(Parcela.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Parcela> porPedido(Pedido pedido) {
		String jpql = "from Parcela p where p.pedido.id = :id";
		Query query = manager.createQuery(jpql);
		query.setParameter("id", pedido.getId());
		return query.getResultList();

	}

	@Transactional
	public Parcela salvar(Parcela parcela) {
		return this.manager.merge(parcela);
	}
}
