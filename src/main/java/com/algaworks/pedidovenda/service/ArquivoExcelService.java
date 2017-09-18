package com.algaworks.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.ArquivoExcel;
import com.algaworks.pedidovenda.repository.ArquivoExcelDAO;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class ArquivoExcelService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ArquivoExcelDAO arquivoExcelDAO;

	@Transactional
	public ArquivoExcel salvar(ArquivoExcel arquivoExcel){
		
		//arquivoExcel = this.arquivoExcelDAO.salvar(arquivoExcel);
		
		if(arquivoExcel != null){
			arquivoExcel = this.arquivoExcelDAO.salvar(arquivoExcel);
			System.out.println("salvouuuuuu");
		}else{
			System.err.println("nao salvou");
		}
		
		//return this.arquivoExcelDAO.salvar(arquivoExcel);
		return arquivoExcel;
	}
}
