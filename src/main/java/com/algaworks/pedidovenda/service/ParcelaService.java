package com.algaworks.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Parcela;
import com.algaworks.pedidovenda.repository.ParcelaDAO;
import com.algaworks.pedidovenda.util.jpa.Transactional;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

public class ParcelaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ParcelaDAO parcelaDAO;

	@Transactional
	public Parcela salvar(Parcela parcela) {
		
		if(!parcela.getPaga())
			parcela.setPaga(true);		
	
		
			parcela = parcelaDAO.salvar(parcela);

		
		if(parcela != null){
			FacesUtil.InfoMessage("a parcela " + parcela.getDescricao() + " foi paga");
			
		}
		return parcela;
		
	}
}
