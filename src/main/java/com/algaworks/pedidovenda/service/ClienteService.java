package com.algaworks.pedidovenda.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.model.Parcela;
import com.algaworks.pedidovenda.repository.ClienteDAO;
import com.algaworks.pedidovenda.repository.filter.ClienteParaPesquisa;
import com.algaworks.pedidovenda.util.jpa.Transactional;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

public class ClienteService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteDAO clienteDAO;

	public List<Cliente> listarPorNome(String nome) {
		return clienteDAO.porNome(nome);
	}

	@Transactional
	public Cliente salvar(Cliente cliente) {
		Cliente clienteExistente = clienteDAO.porCPF(cliente);

		// if (produtoExistente != null && !produtoExistente.equals(produto)) {
		// throw new
		// NegocioException("Já existe um produto com o SKU informado.");
		// }

		if (clienteExistente != null && !clienteExistente.equals(cliente)) {
			FacesUtil.AvisoMessage("Ja existe um cliente com esse CPF");
		} else {
			try {
				if (cliente.getId() == null) {
					cliente.setDataCadastro(new Date());
				}
				cliente = clienteDAO.salvar(cliente);
				FacesUtil.InfoMessage("Cliente salvo com sucesso!");
			} catch (Exception e) {
				FacesUtil.ErrorMessage("Erro ao tentar salvar o cliente");
			}

		}

		return cliente;
	}

	public List<Cliente> listaAniversariantes(){
		return clienteDAO.listarAniversariantes();
	}
	
	public Double porValorPendente(Cliente cliente){
		List<Parcela> parcelas = clienteDAO.porValorPendente(cliente);
		
		Double valorPendente = 0.0;
		
		for(Parcela parcela : parcelas){
			valorPendente +=parcela.getValorParcela();
		}	
		
		return valorPendente;
	}
	
	public List<Cliente> pesquisar(ClienteParaPesquisa filtro) {

		List<Cliente> lista = null;

		if (StringUtils.isNotBlank(filtro.getCpf())
				&& StringUtils.isNotBlank(filtro.getNome())) {
			FacesUtil.AvisoMessage("Preencha apenas um dos campos abaixo");
			return null;
		}
		
		if (StringUtils.isEmpty(filtro.getCpf())
				&& StringUtils.isEmpty(filtro.getNome())) {
			lista = clienteDAO.listarTodos();
		}
		
		
		if(StringUtils.isNotBlank(filtro.getNome())){
			lista = clienteDAO.porNome(filtro.getNome().toUpperCase());
		}
		

		if(StringUtils.isNotBlank(filtro.getCpf())){
				lista = clienteDAO.pesquisar(filtro);
		}
		
		if (lista.isEmpty()) {
			FacesUtil.AvisoMessage("Nenhum Cliente encontrado!");
			return null;
		}

		return lista;
	}

	public void remover(Cliente cliente) {
		try {

			this.clienteDAO.remover(cliente);
			FacesUtil.AvisoMessage("Cliente removido com sucesso!");
		} catch (Exception e) {
			FacesUtil.ErrorMessage("Erro ao tentar remover o Cliente "
					+ cliente.getNome());
		}

	}

	
}
