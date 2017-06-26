package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

//import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import com.algaworks.pedidovenda.model.Parcela;
import com.algaworks.pedidovenda.model.Pedido;
import com.algaworks.pedidovenda.model.Pedido2;
import com.algaworks.pedidovenda.model.Usuario;
import com.algaworks.pedidovenda.model.vo.DataValor;
import com.algaworks.pedidovenda.repository.filter.PedidoParaPesquisa;

public class PedidoDAO2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Pedido2> filtrados(PedidoParaPesquisa pedido) {
		Session session = this.manager.unwrap(Session.class);

		Criteria criteria = session.createCriteria(Pedido2.class)
				.createAlias("cliente", "c") // associação join com cliente e
												// nomeamos como "c"
				.createAlias("vendedor", "v"); // associação join com usuario e
												// nomeamos como "v"

		if (pedido.getNumeroDe() != null) {
			// id deve ser maior ou igual (ge = greater or equals) a
			// pedido.numeroDe
			criteria.add(Restrictions.ge("id", pedido.getNumeroDe()));
		}

		if (pedido.getNumeroAte() != null) {
			// id deve ser menor ou igual (le = lower or equals) a
			// pedido.numeroDe
			criteria.add(Restrictions.le("id", pedido.getNumeroAte()));
		}

		if (pedido.getDataCriacaoDe() != null) {
			// id deve ser maior ou igual (ge = greater or equals) a
			// pedido.numeroDe
			criteria.add(Restrictions.ge("dataCriacao",
					pedido.getDataCriacaoDe()));
		}

		if (pedido.getDataCriacaoAte() != null) {
			// id deve ser menor ou igual (le = lower or equals) a
			// pedido.numeroDe
			criteria.add(Restrictions.le("dataCriacao",
					pedido.getDataCriacaoAte()));
		}

		if (StringUtils.isNotBlank(pedido.getNomeCliente())) {
			// acessamos o nome do cliente associado ao pedido pelo alias "c"
			criteria.add(Restrictions.ilike("c.nome", pedido.getNomeCliente(),
					MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(pedido.getNomeVendedor())) {
			// acessamos o nome do vendedor associado ao pedido pelo alias "v"
			criteria.add(Restrictions.ilike("v.nome", pedido.getNomeVendedor(),
					MatchMode.ANYWHERE));
		}

		if (pedido.getStatuses() != null && pedido.getStatuses().length > 0) {
			// adicionamos uma restrição "in", passando um array de constantes
			// da ENUM StatusPedido
			criteria.add(Restrictions.in("status", pedido.getStatuses()));
		}

		return criteria.addOrder(Order.desc("id")).list();
	}

	public Pedido2 porId(Long id) {
		return this.manager.find(Pedido2.class, id);
	}
	
	public Pedido2 porId(Pedido2 pedido) {
		return this.manager.find(Pedido2.class, pedido.getId());
	}

	// crud
	public Pedido2 salvar(Pedido2 pedido) {
		return manager.merge(pedido);
	}

	@SuppressWarnings({ "unchecked" })
	public Map<Date, BigDecimal> valoresTotaisPorData(Integer numeroDeDias,
			Usuario criadoPor) {

		numeroDeDias -= 1;
		Calendar dataInicial = Calendar.getInstance(); // data atual do SO

		dataInicial = DateUtils.truncate(dataInicial, Calendar.DAY_OF_MONTH);
		dataInicial.add(Calendar.DAY_OF_MONTH, numeroDeDias * -1);

		Map<Date, BigDecimal> resultado = criarMapaVazio(numeroDeDias,
				dataInicial);

		Session sessao = this.manager.unwrap(Session.class);
		// Session sessao = manager.unwrap(Session.class);
		Criteria criteria = sessao.createCriteria(Pedido2.class);

		// ex.: select date(data_criacao) as data, sum(valorTotal) as valor from
		// pedido where
		// data_criacao >= :dataInicial and vendedor_id = : criadoPor
		// group by date(data_criacao)

		criteria.setProjection(
				Projections.projectionList()
						// projeção da data
						.add(Projections.sqlGroupProjection(
								"date(data_criacao) as data",
								"date(data_criacao)", new String[] { "data" },
								new Type[] { StandardBasicTypes.DATE }))
						// projeção valor total - o valor total citado abaixo é
						// do objeto
						.add(Projections.sum("valorTotal").as("valor"))).add(
				Restrictions.ge("dataCriacao", dataInicial.getTime()));

		if (criadoPor != null) {
			criteria.add(Restrictions.eq("vendedor", criadoPor));
		}

		List<DataValor> valoresPorData = criteria.setResultTransformer(
				Transformers.aliasToBean(DataValor.class)).list();

		for (DataValor dataValor : valoresPorData) {
			resultado.put(dataValor.getData(), dataValor.getValor());
		}

		return resultado;
	}

	private static Map<Date, BigDecimal> criarMapaVazio(Integer numeroDeDias,
			Calendar dataInicial) {

		dataInicial = (Calendar) dataInicial.clone();
		Map<Date, BigDecimal> mapaInicial = new TreeMap<>();

		for (int i = 0; i <= numeroDeDias; i++) {
			mapaInicial.put(dataInicial.getTime(), BigDecimal.ZERO);
			dataInicial.add(Calendar.DAY_OF_MONTH, 1);
		}
		return mapaInicial;
	}

}
