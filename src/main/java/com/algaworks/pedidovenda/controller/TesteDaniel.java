package com.algaworks.pedidovenda.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.algaworks.pedidovenda.util.jpa.Transactional;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Entity
public class TesteDaniel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	private String nome;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}

class TesteDanielDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@Transactional
	public TesteDaniel salvar(TesteDaniel testeDaniel) {
		return this.manager.merge(testeDaniel);
	}

}

class TesteDanielService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TesteDanielDAO testeDanielDAO;

	public TesteDaniel salvar(TesteDaniel testeDaniel) {
		testeDaniel = this.testeDanielDAO.salvar(testeDaniel);

		if (testeDaniel != null) {
			FacesUtil.InfoMessage("Apenas um arquivo");
		} else {
			FacesUtil.AvisoMessage("deu erro =P");
		}

		return testeDaniel;
	}

}

@Named
@SessionScoped
class TesteDanielBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private TesteDaniel testeDaniel;

	@Inject
	private TesteDanielService testeDanielService;

	public TesteDanielBean() {
		testeDaniel = new TesteDaniel();
	}

	public void salvar() {
		this.testeDanielService.salvar(testeDaniel);
	}

	public TesteDaniel getTesteDaniel() {
		return testeDaniel;
	}

	public void setTesteDaniel(TesteDaniel testeDaniel) {
		this.testeDaniel = testeDaniel;
	}
}
