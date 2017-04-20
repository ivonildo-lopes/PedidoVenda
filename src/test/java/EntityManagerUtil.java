import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	
	private static EntityManagerFactory fabrica = null;
	private static EntityManager em = null;
	
	public static EntityManager getEntityManager(){
		if(fabrica == null){
			fabrica = Persistence.createEntityManagerFactory("PedidoPU");
		}
		if(em == null){
			em = fabrica.createEntityManager();
		}
		
		return em;
			
		
	}
	
	public void abrirConexao(){
		em.getTransaction().begin();
	}
	
	public void comitar(){
		em.getTransaction().commit();
	}
	
	public void fecharConexao(){
		
	}
	
	

}
