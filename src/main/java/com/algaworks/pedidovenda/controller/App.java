package com.algaworks.pedidovenda.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	//enviar email
	public static void main(String[] args) {
		ApplicationContext context =
	             new ClassPathXmlApplicationContext("Spring-Mail.xml");

	    	MailMail mm = (MailMail) context.getBean("mailMail");
	        mm.sendMail("ivonildolopes@gmail.com",
	    		   "ivonildolopes.ti@gmail.com",
	    		   "Testing123",
	    		   "Testing only \n\n Hello Spring Email Sender");
	}

}
