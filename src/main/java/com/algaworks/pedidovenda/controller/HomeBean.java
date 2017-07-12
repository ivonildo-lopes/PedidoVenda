package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.service.ClienteService;
import com.algaworks.pedidovenda.util.Datas;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class HomeBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteService clienteService;
	
	private List<Cliente> listaClientes = new ArrayList<>();
	
//	@PostConstruct
//	public void init(){
//		//listaClientes = clienteService.listaAniversariantes();
//	}
	
	
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {	
		}
		
		enviaEmailParaAniversariantes();
	}
	
	public void enviaEmailParaAniversariantes(){
		try {
			listaClientes = clienteService.listaAniversariantes();
			for(Cliente c : listaClientes){
				//envio de email
				ApplicationContext context =
			             new ClassPathXmlApplicationContext("Spring-Mail.xml");

			    	MailMail mm = (MailMail) context.getBean("mailMail");
			        mm.sendMail("ivonildolopes@gmail.com",
			    		   c.getEmail(),
			    		   "Cliente: " + c.getNome(),
			    		   "Parabens pelos seus " + Datas.calculaIdade(c.getDataNascimento()) + " anos" );
			}
			
			FacesUtil.InfoMessage("Um email foi enviado aos clientes");
		} catch (Exception e) {
			// TODO: handle exception
			FacesUtil.ErrorMessage("erro ao tentar enviar email");
		}
		
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	
	
	
}
