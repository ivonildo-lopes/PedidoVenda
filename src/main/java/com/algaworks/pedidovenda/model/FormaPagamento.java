package com.algaworks.pedidovenda.model;

public enum FormaPagamento {

	DINHEIRO("DINHEIRO"),
	CARTAO_CREDITO("CARTÃO DE CRÉDITO"), 
	CARTAO_DEBITO("CARTÃO DE DÉBITO"), 
	CHEQUE("CHEQUE"), 
	BOLETO_BANCARIO("BOLETO BANCÁRIO"), 
	DEPOSITO_BANCARIO("DEPÓSITO BANCÁRIO");
	
	private String descricao;
	
	FormaPagamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	
	
}