import java.text.ParseException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.model.Parcela;
import com.algaworks.pedidovenda.model.Pedido;

public class teste {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		// EntityManagerFactory emf =
		// Persistence.createEntityManagerFactory("PedidoPU");
		// EntityManager em = emf.createEntityManager();

		//
		EntityManager em = EntityManagerUtil.getEntityManager();
		//
		// Categoria c = em.find(Categoria.class, 1L);
		// System.out.println("AQUIIIIIIIIIIIIIIIIIIIIII");
		// System.out.println(c.getDescricao());

		// ClienteParaPesquisa filtro = new ClienteParaPesquisa();
		// //filtro.setCpf("031.741.803-33");
		// filtro.setNome("IVO");
		// String jpql = null;
		//
		// if(StringUtils.isNotBlank(filtro.getCpf())){
		// jpql =
		// "from Cliente c where c.documentoReceitaFederal = :documentoReceitaFederal";
		// }
		//
		// if(StringUtils.isNotBlank(filtro.getNome())){
		// jpql = "from Cliente c where c.nome like :nome";
		// }
		//
		// Query query = em.createQuery(jpql,Cliente.class);
		// //query.setParameter("documentoReceitaFederal", filtro.getCpf());
		// query.setParameter("nome", "%" + filtro.getNome() + "%");
		//
		// //Cliente cliente = (Cliente) query.getSingleResult();
		//
		// List<Cliente> cliente = query.getResultList();
		//
		// for(Cliente c : cliente){
		// System.out.println(c.getNome());
		// }

		// Date data = new Date();
		// int m = Datas.pegarMes(data);
		// int d= Datas.pegarDia(data);
		//
		// List<Cliente> lista = new ArrayList<>();
		//
		// String jpql =
		// "from Cliente where extract('day' from dataNascimento) = :dia and "
		// + "extract('month' from dataNascimento) = :mes";
		// Query queru = em.createQuery(jpql);
		// queru.setParameter("dia", d);
		// queru.setParameter("mes", m);
		//
		// lista = queru.getResultList();
		//
		//
		// for(Cliente cliente : lista){
		// System.out.println(cliente.getNome() + " nasceu nessa data: " +
		// cliente.getDataNascimento());
		// }

		// Pedido pedido = new Pedido();
		// pedido.setId(124L);
		//
		// String jpql = "from Parcela p where p.pedido.id = :id";
		// Query query = em.createQuery(jpql);
		// query.setParameter("id", pedido.getId());
		//
		// List<Parcela> parcelas = new ArrayList<>();
		//
		// parcelas = query.getResultList();
		//
		// for(Parcela p : parcelas){
		// System.out.println(p.getDescricao());
		// }

		// String i = JOptionPane.showInputDialog("numero do pedido");
		// String jpql = "select sum(p.valorTotal) from Pedido p where p.id = "
		// + i + " and p.status = 'EMITIDO'";

		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// String data = JOptionPane.showInputDialog("numero do pedido");
		// String jpql =
		// "select sum(p.valorTotal) from Pedido p where date(p.dataEmissao) = '2016-12-26' and p.status = 'EMITIDO'";

		// funciona
		// Date data = Datas.informarData2("2016-12-26");
		// String jpql =
		// "select sum(p.valorTotal) from Pedido p where date(p.dataEmissao) = :data and p.status = 'EMITIDO'";
		// Query query = em.createQuery(jpql);
		// query.setParameter("data", data);
		//
		// BigDecimal valor = (BigDecimal) query.getSingleResult();
		//
		// if(valor == null)
		// System.out.println("Não ouve vendas nesse dia");
		//
		// System.out.println(valor);

		//String jpql = "select count(p.pedido) as quantidade from Parcela p join p.pedido pp join Cliente c where p.paga = 'f' and pp.cliente.nome = 'IGOR MELÃO'";
//		SELECT * from Pessoa pessoa
//		JOIN Endereco endereco ON endereco.pessoa_id = pessoa.id
//		WHERE endereco.rua LIKE "%Alameda%"
		
		
		//funciona - usado no clienteDAO, PedidoDAO
//		
//		Cliente cliente = new Cliente();
//		
//		cliente = em.find(Cliente.class, 1L);
//		
//		
//		//soma o valor das parcelas 
//		String jpql = "select parcela from Parcela parcela inner join parcela.pedido pp inner join pp.cliente c"
//				+ " where c.nome = :nome AND parcela.paga = false ";// p inner join Pedido pp on p.id_pedido = p.id";
//		Query query = em.createQuery(jpql);
//		query.setParameter("nome",cliente.getNome());
//		
//		List<Parcela> valores = query.getResultList();
//
//		double soma = 0;
//		int contador = 0;
//		for(Parcela valor : valores){
//			soma +=valor.getValorParcela();
//			contador += 1;
//		}	
//			
//		System.out.println("quantidade: " + soma + " " + contador);
		
		
		Pedido pedido = em.find(Pedido.class, 146L);
		
//		String jpql = "select p as quantidade from Parcela p inner join p.pedido pp"
//		+ " where p.paga = false and p.pedido.id = 146";
		
		String jpql = "select p as quantidade from Parcela p inner join p.pedido pp"
				+ " where p.paga = false and p.pedido.id = :numeroPedido";


		Query query = em.createQuery(jpql);
		query.setParameter("numeroPedido", pedido.getId());
		
		List<Parcela> valores = query.getResultList();

		double soma = 0;
		int contador = 0;
		for(Parcela valor : valores){
			soma +=valor.getValorParcela();
			contador += 1;
		}	
			
		System.out.println("quantidade: " + soma + " " + contador);

		


	}

}
