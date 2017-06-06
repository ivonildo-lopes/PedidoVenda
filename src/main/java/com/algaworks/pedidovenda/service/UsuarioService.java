package com.algaworks.pedidovenda.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Usuario;
import com.algaworks.pedidovenda.repository.UsuarioDAO;
import com.algaworks.pedidovenda.repository.filter.UsuarioParaPesquisa;
import com.algaworks.pedidovenda.util.jpa.Transactional;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

public class UsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Transactional
	public Usuario salvar(Usuario usuario) {

		try {
			usuario = usuarioDAO.salvar(usuario);
			FacesUtil.InfoMessage("Usuario salvo com sucesso!");
		} catch (Exception e) {
			FacesUtil.ErrorMessage("Erro ao tentar salvar o usuario");
		}

		return usuario;
	}

	public List<Usuario> listaVendedores() {
		return usuarioDAO.listarVendedores();
	}
	
	public List<Usuario> pesquisar(UsuarioParaPesquisa usuarioParaPesquisa) {
		return usuarioDAO.pesquisar(usuarioParaPesquisa.getNome());
	}
}
