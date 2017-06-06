package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

//import org.apache.commons.lang.StringUtils;

import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.model.Parcela;
import com.algaworks.pedidovenda.repository.filter.ClienteParaPesquisa;
import com.algaworks.pedidovenda.util.Datas;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class ClienteDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Cliente porId(Long id) {
		return this.manager.find(Cliente.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> listarTodos() {
		String jpql = "from Cliente ORDER BY nome ASC";
		Query query = manager.createQuery(jpql, Cliente.class);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> listarAniversariantes(){
		int dia = Datas.pegarDia(new Date());
		int mes = Datas.pegarMes(new Date());
		
		String jpql = "from Cliente where extract('day' from dataNascimento) = :dia and "
				+ "extract('month' from dataNascimento) = :mes ORDER BY nome ASC";
		Query query = manager.createQuery(jpql);
		query.setParameter("dia", dia);
		query.setParameter("mes", mes);
		return query.getResultList();
	}

	public Cliente porCPF(Cliente cliente) {
		String jpql = "from Cliente c where c.documentoReceitaFederal = :documentoReceitaFederal";
		try {
			Query query = manager.createQuery(jpql, Cliente.class);
			query.setParameter("documentoReceitaFederal",
					cliente.getDocumentoReceitaFederal());

			return (Cliente) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> porNome(String nome) {
		String jpql = "from Cliente c where upper(c.nome) like :nome ORDER BY nome ASC";
		Query query = manager.createQuery(jpql, Cliente.class);
		query.setParameter("nome", "%" + nome.toUpperCase() + "%");
		return query.getResultList();
	}

	
	@SuppressWarnings("unchecked")
	public List<Parcela> porValorPendente(Cliente cliente){
		String jpql = "select parcela from Parcela parcela inner join parcela.pedido pp inner join pp.cliente c"
				+ " where c.nome = :nome AND parcela.paga = false AND pp.status = 'EMITIDO'";
		Query query = manager.createQuery(jpql);
		query.setParameter("nome",cliente.getNome());
		
		return query.getResultList();
	}
	

	@SuppressWarnings("unchecked")
	public List<Cliente> pesquisar(ClienteParaPesquisa filtro) {
		
		String jpql = null;
		
		if(StringUtils.isNotBlank(filtro.getCpf())){
			jpql = "from Cliente c where c.documentoReceitaFederal = :documentoReceitaFederal";
		}
		
//		if(StringUtils.isNotBlank(filtro.getNome())){
//			jpql = "from Cliente c where c.nome like :nome";
//		}
		
		if(StringUtils.isBlank(filtro.getCpf()) &&
				StringUtils.isBlank(filtro.getCpf())){
			jpql = "from Cliente ORDER BY nome ASC";
		}
		
		Query query = manager.createQuery(jpql,Cliente.class);
		if(StringUtils.isNotBlank(filtro.getCpf())){
			query.setParameter("documentoReceitaFederal", filtro.getCpf());
		}
		
//		if(StringUtils.isNotBlank(filtro.getNome())){
//			query.setParameter("nome", "%" + filtro.getNome() + "%");
//		}
				
		return query.getResultList();
	}


	public Cliente salvar(Cliente cliente) {
		return manager.merge(cliente);
	}
	
	@Transactional
	public void remover(Cliente cliente) {
		this.manager.remove(cliente);

	}

}
