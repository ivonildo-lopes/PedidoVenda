package com.algaworks.pedidovenda.model;

public enum FormaPagamento {

	DINHEIRO("DINHEIRO"),
	CARTAO_CREDITO2("CARTÃO DE CRÉDITO"),
	CARTAO_DEBITO("CARTÃO DE DÉBITO"), 
	CARTAO_CREDITO("FIADO"), //COLOQUEI COMO CARTAO POIS ESTAVA FAZENDO UM TESTE POREM EQUIVALE A FIADO
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