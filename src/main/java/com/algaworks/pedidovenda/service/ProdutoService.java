package com.algaworks.pedidovenda.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.algaworks.pedidovenda.model.Produto;
import com.algaworks.pedidovenda.repository.ProdutoDAO;
import com.algaworks.pedidovenda.repository.filter.ProdutoParaPesquisa;
import com.algaworks.pedidovenda.util.jpa.Transactional;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

public class ProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoDAO produtos;
	
	@Transactional
	public Produto salvar(Produto produto) {
		Produto produtoExistente = produtos.porSku(produto.getSku());
		
		if (produtoExistente != null && !produtoExistente.equals(produto)) {
			throw new NegocioException("Já existe um produto com o SKU informado.");
		}
		
		return produtos.guardar(produto);
	}
	
	@Transactional
	public void remover(Produto produto){
		produtos.remover(produto);
	}
	
	public List<Produto> filtrados(ProdutoParaPesquisa filtro) {
		List<Produto> lista = null;
		
		if(StringUtils.isNotBlank(filtro.getSku()) && StringUtils.isNotBlank(filtro.getNome())){
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
	
	
	public List<Produto> porNome(String nome){
		return this.produtos.porNome(nome.toUpperCase());
	}
	
	public Produto porSku(String sku){
		return this.produtos.porSku(sku);
	}
	
	public List<Produto> listarTodos(){
		return this.produtos.listarTodos();
	}
}
