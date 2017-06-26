package com.algaworks.pedidovenda.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Fornecedores")
public class Fornecedor implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	private String nome;
	
	private String apelido;
	
	@Column
	private String endereco;
	
	@Column(nullable = false)
	private String telefone;
	
	private boolean isWhatsApp;
	
	private String email;
	
	private String cpfCnpj;
	
	@Column(nullable = false)
	private String categoria;
	
	private String detalhes;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20,name="tipopessoa")
	private TipoPessoa tipo;
	
	
	public boolean isPessoaFisica(){
		
		if(tipo == TipoPessoa.FISICA){
			return true;
		}
		return false;
	}
	
	//get and set
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public boolean isWhatsApp() {
		return isWhatsApp;
	}

	public void setWhatsApp(boolean isWhatsApp) {
		this.isWhatsApp = isWhatsApp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public TipoPessoa getTipo() {
		return tipo;
	}

	public void setTipo(TipoPessoa tipo) {
		this.tipo = tipo;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
}
