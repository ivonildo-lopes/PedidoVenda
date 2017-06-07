package com.algaworks.pedidovenda.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Parcela;
import com.algaworks.pedidovenda.model.Pedido;
import com.algaworks.pedidovenda.model.StatusPedido;
import com.algaworks.pedidovenda.service.ParcelaService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;
import com.algaworks.pedidovenda.validation.PedidoEdicao;

@Named
@ViewScoped
public class ParcelaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ParcelaService parcelaService;

	@PedidoEdicao
	@Inject
	private Pedido pedido;
	
	public void salvar(Parcela parcela) {
		System.out.println(parcela.toString());
		if(parcela.getPedido().getStatus() == StatusPedido.EMITIDO){
			parcela = parcelaService.salvar(parcela);
		}else{
			FacesUtil.AvisoMessage("O pedido não esta emitido por isso a parcela não pode ser paga");
		}
			
	}

//	public Parcela getParcela() {
//		return parcela;
//	}
//
//	public void setParcela(Parcela parcela) {
//		this.parcela = parcela;
//	}

	public ParcelaService getParcelaService() {
		return parcelaService;
	}

	public void setParcelaService(ParcelaService parcelaService) {
		this.parcelaService = parcelaService;
	}

}
