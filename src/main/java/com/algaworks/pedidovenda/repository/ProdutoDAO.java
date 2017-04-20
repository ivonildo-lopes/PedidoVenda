package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.algaworks.pedidovenda.model.Produto;
import com.algaworks.pedidovenda.repository.filter.ProdutoParaPesquisa;
import com.algaworks.pedidovenda.service.NegocioException;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class ProdutoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Produto guardar(Produto produto) {
		return manager.merge(produto);
	}

	@Transactional
	public void remover(Produto produto) {
		try {
			produto = porId(produto.getId());
			manager.remove(produto);
			manager.flush();

		} catch (PersistenceException e) {
			// TODO: handle exception
			throw new NegocioException("Produto nao pode ser excluido");
		}
	}

	public Produto porSku(String sku) {
		try {
			return manager
					.createQuery("from Produto where upper(sku) = :sku",
							Produto.class)
					.setParameter("sku", sku.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Produto> filtrados(ProdutoParaPesquisa filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Produto.class);

		if (StringUtils.isNotBlank(filtro.getSku())) {
			criteria.add(Restrictions.eq("sku", filtro.getSku()));
		}

		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome().toUpperCase(),
					MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

	public Produto porId(Long id) {

		return manager.find(Produto.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Produto> porNome(String nome) {
		String jpql = "from Produto p where p.nome like :nome ORDER BY nome ASC";
		Query query = manager.createQuery(jpql, Produto.class);
		query.setParameter("nome", "%" + nome.toUpperCase() + "%");

		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> listarTodos(){
		String jpql = "from Produto ORDER BY nome ASC";
		Query query = manager.createQuery(jpql,Produto.class);
		return query.getResultList();
	}

}
