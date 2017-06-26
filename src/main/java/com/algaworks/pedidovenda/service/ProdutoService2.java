package com.algaworks.pedidovenda.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.algaworks.pedidovenda.model.Produto;
import com.algaworks.pedidovenda.model.Produto2;
import com.algaworks.pedidovenda.repository.ProdutoDAO2;
import com.algaworks.pedidovenda.repository.filter.ProdutoParaPesquisa;
import com.algaworks.pedidovenda.util.jpa.Transactional;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

public class ProdutoService2 implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoDAO2 produtos;
	
	@Transactional
	public Produto2 salvar(Produto2 produto) {
		return produtos.guardar(produto);
	}
	
	@Transactional
	public void remover(Produto2 produto){
		produtos.remover(produto);
	}
	
	public List<Produto2> filtrados(ProdutoParaPesquisa filtro) {
		List<Produto2> lista = null;
		
		if(StringUtils.isNotBlank(filtro.getNome())){
			FacesUtil.AvisoMessage("Preencha apenas um dos campos abaixo");
			return null;
		}
		
		lista = produtos.filtrados(filtro);
		
		if(lista.isEmpty()){
			FacesUtil.AvisoMessage("Nenhum produto encontrado!");
			return null;
		}
		
		return lista;
	}
	
	
	public List<Produto2> porNome(String nome){
		return this.produtos.porNome(nome.toUpperCase());
	}
	
	public List<Produto2> listarTodos(){
		return this.produtos.listarTodos();
	}
}
