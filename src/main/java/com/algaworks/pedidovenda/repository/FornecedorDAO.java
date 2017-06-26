package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

//import org.apache.commons.lang.StringUtils;

import com.algaworks.pedidovenda.model.Fornecedor;
import com.algaworks.pedidovenda.repository.filter.FornecedorParaPesquisa;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class FornecedorDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Fornecedor porId(Integer id) {
		return this.manager.find(Fornecedor.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Fornecedor> listarTodos() {
		String jpql = "from Fornecedor ORDER BY nome ASC";
		Query query = manager.createQuery(jpql, Fornecedor.class);
		return query.getResultList();
	}

	public Fornecedor porCPF(String cpfCnpj) {
		String jpql = "from Fornecedor f where f.cpfCnpj = :cpfCnpj";
		try {
			Query query = manager.createQuery(jpql, Fornecedor.class);
			query.setParameter("cpfCnpj", cpfCnpj);

			return (Fornecedor) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Fornecedor> porNome(String nome) {
		String jpql = "from Fornecedor f where upper(f.nome) like :nome ORDER BY nome ASC";
		Query query = manager.createQuery(jpql, Fornecedor.class);
		query.setParameter("nome", "%" + nome.toUpperCase() + "%");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Fornecedor> pesquisar(FornecedorParaPesquisa filtro) {

		String jpql = null;

		if (StringUtils.isNotBlank(filtro.getCpf())) {
			jpql = "from Fornecedor f where f.cpfCnpj = :documentoReceitaFederal";
		}

		if (StringUtils.isBlank(filtro.getCpf()) && StringUtils.isBlank(filtro.getCpf())) {
			jpql = "from Fornecedor ORDER BY nome ASC";
		}

		Query query = manager.createQuery(jpql, Fornecedor.class);
		if (StringUtils.isNotBlank(filtro.getCpf())) {
			query.setParameter("documentoReceitaFederal", filtro.getCpf());
		}

		return query.getResultList();
	}

	public Fornecedor salvar(Fornecedor fornecedor) {
		return manager.merge(fornecedor);
	}

	@Transactional
	public void remover(Fornecedor fornecedor) {
		this.manager.remove(fornecedor);

	}

}
