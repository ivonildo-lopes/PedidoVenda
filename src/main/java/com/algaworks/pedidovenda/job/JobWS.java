//package com.algaworks.pedidovenda.job;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.inject.Inject;
//
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.algaworks.pedidovenda.controller.MailMail;
//import com.algaworks.pedidovenda.model.Cliente;
//import com.algaworks.pedidovenda.service.ClienteService;
//import com.algaworks.pedidovenda.util.Datas;
//import com.algaworks.pedidovenda.util.jsf.FacesUtil;
//
//public class JobWS implements Job {
//
//	@Inject
//	private ClienteService clienteService;
//	
//	private List<Cliente> listaClientes = new ArrayList<>();
//	
//	String n;
//	public JobWS() {
//		// TODO Auto-generated constructor stub
//		n = "teste";
//		System.out.println("chamou construtor");
//	}
//	
//	@Override
//	public void execute(JobExecutionContext context) throws JobExecutionException {
//			System.out.println("chamou execute");
//		listaClientes = clienteService.listaAniversariantes();
//		enviaEmailParaAniversariantes();
//	}
//	
//	public void enviaEmailParaAniversariantes(){
//		System.out.println("entrou aqui 2");
//		try {
//			//listaClientes = clienteService.listaAniversariantes();
//			for(Cliente c : listaClientes){
//				//envio de email
//				ApplicationContext context =
//			             new ClassPathXmlApplicationContext("Spring-Mail.xml");
//
//			    	MailMail mm = (MailMail) context.getBean("mailMail");
//			        mm.sendMail("ivonildolopes@gmail.com",
//			    		   c.getEmail(),
//			    		   "Cliente: " + c.getNome(),
//			    		   "Parabens pelos seus " + Datas.calculaIdade(c.getDataNascimento()) + " anos" );
//			}
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
//	}
//
//}
