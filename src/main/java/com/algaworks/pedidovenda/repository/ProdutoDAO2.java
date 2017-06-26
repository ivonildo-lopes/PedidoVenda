package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.algaworks.pedidovenda.model.Produto;
import com.algaworks.pedidovenda.model.Produto2;
import com.algaworks.pedidovenda.repository.filter.ProdutoParaPesquisa;
import com.algaworks.pedidovenda.service.NegocioException;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class ProdutoDAO2 implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Produto2 guardar(Produto2 produto) {
		return manager.merge(produto);
	}

	@Transactional
	public void remover(Produto2 produto) {
		try {
			produto = porId(produto.getId());
			manager.remove(produto);
			manager.flush();

		} catch (PersistenceException e) {
			// TODO: handle exception
			throw new NegocioException("Produto2 nao pode ser excluido");
		}
	}


	@SuppressWarnings("unchecked")
	public List<Produto2> filtrados(ProdutoParaPesquisa filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Produto2.class);

		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome().toUpperCase(),
					MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

	public Produto2 porId(Long id) {

		return manager.find(Produto2.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Produto2> porNome(String nome) {
		String jpql = "from Produto2 p where p.nome like :nome ORDER BY nome ASC";
		Query query = manager.createQuery(jpql, Produto2.class);
		query.setParameter("nome", "%" + nome.toUpperCase() + "%");

		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto2> listarTodos(){
		String jpql = "from Produto2 ORDER BY nome ASC";
		Query query = manager.createQuery(jpql,Produto2.class);
		return query.getResultList();
	}

}
