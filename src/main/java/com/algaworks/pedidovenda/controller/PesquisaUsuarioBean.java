package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Usuario;
import com.algaworks.pedidovenda.repository.filter.UsuarioParaPesquisa;
import com.algaworks.pedidovenda.service.UsuarioService;

@Named
@ViewScoped
public class PesquisaUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioService usuarioService;

	private Usuario usuarioSelecionado;

	private List<Usuario> listaUsuario = new ArrayList<Usuario>();

	private UsuarioParaPesquisa usuarioParaPesquisa;

	public PesquisaUsuarioBean() {
		usuarioParaPesquisa = new UsuarioParaPesquisa();
	}

	public void pesquisar() {
		listaUsuario = usuarioService.pesquisar(usuarioParaPesquisa);
	}
	
	
	// GET AND SET
	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public UsuarioParaPesquisa getUsuarioParaPesquisa() {
		return usuarioParaPesquisa;
	}

	public void setUsuarioParaPesquisa(UsuarioParaPesquisa usuarioParaPesquisa) {
		this.usuarioParaPesquisa = usuarioParaPesquisa;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}
}
