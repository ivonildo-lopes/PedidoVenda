package com.algaworks.pedidovenda.model;

public enum StatusPedido {

	ORCAMENTO("ORÇAMENTO"), EMITIDO("EMITIDO"), CANCELADO("CANCELADO");
	
	private String descricao;
	
	private StatusPedido(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	
}
