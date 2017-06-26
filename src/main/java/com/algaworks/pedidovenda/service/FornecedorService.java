package com.algaworks.pedidovenda.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.algaworks.pedidovenda.model.Fornecedor;
import com.algaworks.pedidovenda.repository.FornecedorDAO;
import com.algaworks.pedidovenda.repository.filter.FornecedorParaPesquisa;
import com.algaworks.pedidovenda.util.jpa.Transactional;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

public class FornecedorService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FornecedorDAO fornecedorDAO;

	public List<Fornecedor> listarPorNome(String nome) {
		return fornecedorDAO.porNome(nome);
	}

	@Transactional
	public Fornecedor salvar(Fornecedor fornecedor) {
		Fornecedor fornecedorExistente = fornecedorDAO.porCPF(fornecedor.getCpfCnpj());

		if (fornecedorExistente != null && !fornecedorExistente.equals(fornecedor)) {
			FacesUtil.AvisoMessage("Ja existe um fornecedor com esse CPF/CNPJ");
		} else {
			try {
				fornecedor = fornecedorDAO.salvar(fornecedor);
				FacesUtil.InfoMessage("Fornecedor salvo com sucesso!");
			} catch (Exception e) {
				FacesUtil.ErrorMessage("Erro ao tentar salvar o fornecedor");
			}

		}

		return fornecedor;
	}

	public List<Fornecedor> pesquisar(FornecedorParaPesquisa filtro) {

		List<Fornecedor> lista = null;

		if (StringUtils.isNotBlank(filtro.getCpf())
				&& StringUtils.isNotBlank(filtro.getNome())) {
			FacesUtil.AvisoMessage("Preencha apenas um dos campos abaixo");
			return null;
		}
		
		if (StringUtils.isEmpty(filtro.getCpf())
				&& StringUtils.isEmpty(filtro.getNome())) {
			lista = fornecedorDAO.listarTodos();
		}
		
		
		if(StringUtils.isNotBlank(filtro.getNome())){
			lista = fornecedorDAO.porNome(filtro.getNome().toUpperCase());
		}
		

		if(StringUtils.isNotBlank(filtro.getCpf())){
				lista = fornecedorDAO.pesquisar(filtro);
		}
		
		if (lista.isEmpty()) {
			FacesUtil.AvisoMessage("Nenhum Fornecedor encontrado!");
			return null;
		}

		return lista;
	}

	public void remover(Fornecedor fornecedor) {
		try {

			this.fornecedorDAO.remover(fornecedor);
			FacesUtil.AvisoMessage("Fornecedor removido com sucesso!");
		} catch (Exception e) {
			FacesUtil.ErrorMessage("Erro ao tentar remover o Fornecedor "
					+ fornecedor.getNome());
		}

	}

	
}
